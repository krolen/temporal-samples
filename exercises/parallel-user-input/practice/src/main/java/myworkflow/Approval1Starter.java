package myworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import myworkflow.Constants;
import myworkflow.workflows.MyWorkflow;

public class Approval1Starter {

    public static void main(String[] args) throws Exception {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client = WorkflowClient.newInstance(service);

        MyWorkflow workflow = client.newWorkflowStub(MyWorkflow.class, Constants.WF_ID);
        workflow.approval1("Approval1");

        System.exit(0);
    }
}
