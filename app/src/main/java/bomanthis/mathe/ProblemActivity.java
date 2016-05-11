package bomanthis.mathe;

import android.app.Activity;
import android.os.Bundle;

public class ProblemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            ProblemFragment problem = new ProblemFragment();
            problem.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content, problem).commit();
        }
    }
}
