package myworkflow.workflows.impl;

import myworkflow.workflows.Approval2Workflow;

public class Approval2WorkflowImpl implements Approval2Workflow {
    @Override
    public String perform(String input) {
        return "This is the second approval message for input: " + input;
    }
}
