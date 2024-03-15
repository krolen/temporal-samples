package myworkflow.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface Approval2Workflow {

    @WorkflowMethod
    String perform(String input);


}
