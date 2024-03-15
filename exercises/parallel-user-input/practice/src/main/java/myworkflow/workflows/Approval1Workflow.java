package myworkflow.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface Approval1Workflow {

    @WorkflowMethod
    String perform(String input);


}
