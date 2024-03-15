package myworkflow.activities;

import io.temporal.activity.ActivityInterface;
import myworkflow.model.ActivityInput;
import myworkflow.model.ActivityOutput;

@ActivityInterface
public interface MyActivities {

    void workflowStartActivity();

    ActivityOutput performAction(ActivityInput input);


//  TranslationActivityOutput translateTerm(TranslationActivityInput input);

}
