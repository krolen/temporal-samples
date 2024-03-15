package myworkflow.activities;

import io.temporal.workflow.Workflow;
import myworkflow.model.ActivityInput;
import myworkflow.model.ActivityOutput;
import org.slf4j.Logger;

public class MyActivitiesImpl implements MyActivities {

    private static final Logger logger = Workflow.getLogger(MyActivitiesImpl.class);


    public void workflowStartActivity() {
        logger.info("workflowStartActivity");
    }

    @Override
    public ActivityOutput performAction(ActivityInput input) {
        logger.info("performing action");
        return new ActivityOutput("Both messages:" +
                "  note1:" + input.getNote1() +
                "  note2: " + input.getNote2());
    }

}
