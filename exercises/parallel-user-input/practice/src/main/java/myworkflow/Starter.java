package myworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import myworkflow.Constants;
import myworkflow.workflows.MyWorkflow;
import myworkflow.model.WorkflowInput;
import myworkflow.model.WorkflowOutput;

public class Starter {

    public static void main(String[] args) throws Exception {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client = WorkflowClient.newInstance(service);

        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(Constants.WF_ID)
                .setTaskQueue(Constants.taskQueue)
                .build();

        MyWorkflow myWorkflow = client.newWorkflowStub(MyWorkflow.class, options);

        WorkflowInput input = new WorkflowInput("", "");

        WorkflowOutput result = myWorkflow.perform(input);

        System.out.printf("Workflow result: %s\n", result);
        System.exit(0);
    }
}
