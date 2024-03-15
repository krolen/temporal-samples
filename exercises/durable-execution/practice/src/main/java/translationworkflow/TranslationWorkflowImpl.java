package translationworkflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.failure.ApplicationFailure;
import io.temporal.workflow.Workflow;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.Duration;

public class TranslationWorkflowImpl implements TranslationWorkflow {

    private static final Logger LOG = Workflow.getLogger(TranslationWorkflowImpl.class);
    // TODO: Define the Workflow logger

    private final ActivityOptions options =
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(5))
                    .build();

    private final TranslationActivities activities =
            Workflow.newActivityStub(TranslationActivities.class, options);

    @Override
    public TranslationWorkflowOutput sayHelloGoodbye(TranslationWorkflowInput input) {
        String name = input.getName();
        String languageCode = input.getLanguageCode();

        LOG.info("workflow execution started with param: {}", input);

        TranslationActivityInput helloInput = new TranslationActivityInput("hello", languageCode);
        LOG.info("Hello Activity is going to be invoked");
        TranslationActivityOutput helloResult = activities.translateTerm(helloInput);
        String helloMessage = helloResult.getTranslation() + ", " + name;

        LOG.info("Sleeping between translation calls");
        Workflow.sleep(Duration.ofSeconds(30));

        LOG.info("Goodbye Activity is going to be invoked: " + input);
        TranslationActivityInput goodbyeInput = new TranslationActivityInput("goodbye", languageCode);
        TranslationActivityOutput goodbyeResult = activities.translateTerm(goodbyeInput);
        String goodbyeMessage = goodbyeResult.getTranslation() + ", " + name;

        if (true) throw ApplicationFailure.newFailure("I am an exception", IOException.class.getName());
//        if (true) throw new RuntimeException("I am an exception");
        return new TranslationWorkflowOutput(helloMessage, goodbyeMessage);
    }
}
