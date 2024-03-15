package myworkflow.workflows.impl;

import myworkflow.workflows.Approval1Workflow;

public class Approval1WorkflowImpl implements Approval1Workflow {
    @Override
    public String perform(String input) {
        return "This is the first approval message for input:" + input;
    }
}
