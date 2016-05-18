package bomanthis.mathe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
    private int[] ids = new int[]{R.id.answer0, R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4};
    private int timeRemaining = 60000;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            setContentView(R.layout.problem_layout);

            //ProblemFragment problem = new ProblemFragment();
            //problem.setArguments(getIntent().getExtras());
            //getFragmentManager().beginTransaction().add(android.R.id.content, problem).commit();

            final TextView timer = (TextView)findViewById(R.id.timer);
            int sec = timeRemaining/1000;
            int min = timeRemaining/60000;
            sec = sec-min*60;
            String sSec = ""+sec;
            if(sSec.length()==1){
                sSec = "0"+sSec;
            }
            timer.setText(min+":"+sSec);
            handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    timeRemaining = timeRemaining-1000;
                    int seconds = (timeRemaining/1000);
                    int minutes = timeRemaining/60000;
                    seconds = seconds-minutes*60;
                    String stSec = ""+seconds;
                    if(stSec.length()==1){
                        stSec="0"+stSec;
                    }
                    timer.setText(minutes+":"+stSec);
                    if(minutes<=0&&seconds<=30){
                        if(seconds%2==0){
                            timer.setBackgroundColor(Color.alpha(255));
                            timer.setTextColor(Color.RED);
                        }
                        else{
                            timer.setTextColor(Color.BLACK);
                            timer.setBackgroundColor(Color.RED);
                        }
                    }
                    if(timeRemaining>0){
                        handler.postDelayed(this,1000);
                        /*Intent intent = new Intent();
                        intent.setClass(this, ScoreActivity.class);
                        startActivity(intent);*/
                    }
                }
            };
            handler.postDelayed(runnable, 1000);

            nextQuestion();

        }
    }

    public void nextQuestion(){
        Bundle args = getIntent().getExtras();
        p = new Problem(args.getInt("index", 0));
        TextView question = (TextView)findViewById(R.id.question);
        question.setText(p.getQuestion());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        question.setPadding(padding, padding, padding, padding);

        answerChoices = (RadioGroup) findViewById(R.id.answerChoices);
        answerChoices.clearCheck();
        RadioButton[] answers = new RadioButton[5];
        for(int i=0; i<5; i++){
            answers[i] = (RadioButton)findViewById(ids[i]);
            answers[i].setText(p.getAnswers(i));
        }

        Button submit = (Button) findViewById(R.id.submit);
        if(Lessons.problemNum==19){
            submit.setText("SUBMIT");
        }
    }

    public int changeID(int id){
        for(int i=0; i<5; i++){
            if(id==ids[i]) {
                return i;
            }
        }
        return -1;
    }

    public void onClick(View view){
        int selected = answerChoices.getCheckedRadioButtonId();
        if(p.checkAnswer(changeID(selected))) {
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
            /*Intent intent = new Intent();
            intent.setClass(this, ProblemActivity.class);
            intent.putExtra("index", Lessons.index);
            startActivity(intent);*/
            nextQuestion();
        }
    }
}
