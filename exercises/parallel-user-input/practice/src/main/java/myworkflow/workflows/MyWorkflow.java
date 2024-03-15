package myworkflow.workflows;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import myworkflow.model.WorkflowInput;
import myworkflow.model.WorkflowOutput;

@WorkflowInterface
public interface MyWorkflow {

    @WorkflowMethod
    WorkflowOutput perform(WorkflowInput input);

    @SignalMethod
        // should be object instead
    void approval1(String message);

    @SignalMethod
        // should be object instead
    void approval2(String message);

}
