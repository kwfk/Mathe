package bomanthis.mathe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ProblemActivity extends Activity {


    private Problem p;
    private RadioGroup answerChoices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            setContentView(R.layout.problem_layout);
            Bundle args = getIntent().getExtras();
            //ProblemFragment problem = new ProblemFragment();
            //problem.setArguments(getIntent().getExtras());
            //getFragmentManager().beginTransaction().add(android.R.id.content, problem).commit();

            p = new Problem(args.getInt("index", 0));
            TextView question = (TextView)findViewById(R.id.question);
            question.setText(p.getQuestion());
            int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
            question.setPadding(padding, padding, padding, padding);

            answerChoices = (RadioGroup) findViewById(R.id.answerChoices);
            RadioButton[] answers = new RadioButton[5];
            int[] ids = new int[]{R.id.answer0, R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4};
            for(int i=0; i<5; i++){
                answers[i] = (RadioButton)findViewById(ids[i]);
                answers[i].setText(p.getAnswers(i));
                answers[i].setId(i);
            }

            Button submit = (Button) findViewById(R.id.submit);
            if(Lessons.problemNum==19){
                submit.setText("SUBMIT");
            }

        }
    }

    public void onClick(View view){
        int selected = answerChoices.getCheckedRadioButtonId();
        if(p.checkAnswer(selected)) {
            Lessons.correct++;
            Lessons.problemNum++;
            //Toast.makeText(ProblemActivity.this, ""+selected+"\n"+p.checkAnswer(selected), Toast.LENGTH_SHORT).show();
        }
        else{
            Lessons.problemNum++;
            //Toast.makeText(ProblemActivity.this, ""+selected+"\n"+p.checkAnswer(selected), Toast.LENGTH_SHORT).show();
        }
        if(Lessons.problemNum>=20){
            Intent intent = new Intent();
            intent.setClass(this, ScoreActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent();
            intent.setClass(this, ProblemActivity.class);
            intent.putExtra("index", Lessons.index);
            startActivity(intent);
        }
    }
}
