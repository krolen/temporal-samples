package myworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import myworkflow.activities.MyActivitiesImpl;
import myworkflow.workflows.impl.Approval1WorkflowImpl;
import myworkflow.workflows.impl.Approval2WorkflowImpl;
import myworkflow.workflows.impl.MyWorkflowImpl;

public class MyWorker {
    public static void main(String[] args) {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker(Constants.taskQueue);

        worker.registerWorkflowImplementationTypes(MyWorkflowImpl.class, Approval1WorkflowImpl.class, Approval2WorkflowImpl.class);

        worker.registerActivitiesImplementations(new MyActivitiesImpl());

        factory.start();
    }
}
