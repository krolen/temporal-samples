package myworkflow.workflows.impl;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.CompletablePromise;
import io.temporal.workflow.Workflow;
import myworkflow.activities.MyActivities;
import myworkflow.model.ActivityInput;
import myworkflow.model.ActivityOutput;
import myworkflow.model.WorkflowInput;
import myworkflow.model.WorkflowOutput;
import myworkflow.workflows.Approval1Workflow;
import myworkflow.workflows.Approval2Workflow;
import myworkflow.workflows.MyWorkflow;
import org.slf4j.Logger;

import java.time.Duration;

public class MyWorkflowImpl implements MyWorkflow {

    public static final Logger logger = Workflow.getLogger(MyWorkflowImpl.class);

    private final ActivityOptions options =
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(15))
                    .build();

    private final MyActivities activities = Workflow.newActivityStub(MyActivities.class, options);

    private final CompletablePromise<String> approval1Completion = Workflow.newPromise();
    private final CompletablePromise<String> approval2Completion = Workflow.newPromise();

    @Override
    public WorkflowOutput perform(WorkflowInput input) {
        logger.info("Starting workflow");

        activities.workflowStartActivity();
        Workflow.sleep(Duration.ofSeconds(5));

        String approval1 = approval1Completion.get();
        String approval2 = approval2Completion.get();

        ActivityOutput activityOutput = activities.performAction(new ActivityInput(approval1, approval2));
        return new WorkflowOutput(activityOutput.getMessage());
    }

    @Override
    public void approval1(String message) {
        logger.info("Gor approval 1: " + message);
        Approval1Workflow child = Workflow.newChildWorkflowStub(Approval1Workflow.class);
        approval1Completion.completeFrom(Async.function(child::perform, message));
    }

    @Override
    public void approval2(String message) {
        logger.info("Gor approval 2: " + message);
        Approval2Workflow child = Workflow.newChildWorkflowStub(Approval2Workflow.class);
        approval2Completion.completeFrom(Async.function(child::perform, message));
    }


}
