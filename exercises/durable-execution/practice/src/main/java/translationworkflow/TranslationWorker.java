package translationworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class TranslationWorker {

  public static void main(String[] args) {
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkerFactory factory = WorkerFactory.newInstance(client);

    Worker worker = factory.newWorker(Constants.TASK_QUEUE);

    worker.registerWorkflowImplementationTypes(TranslationWorkflowImpl.class);

    worker.registerActivitiesImplementations(new TranslationActivitiesImpl());

    factory.start();

//    factory.getWorkflowClient().fetchHistory("wfId");

//    factory.getWorkflowClient().getWorkflowServiceStubs().blockingStub().listWorkflowExecutions()....
  }
}
