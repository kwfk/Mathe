package bomanthis.mathe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ProblemActivity extends AppCompatActivity {


    private Problem p;
    private RadioGroup answerChoices;
    private int[] ids = new int[]{R.id.answer0, R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4};
    private int timeRemaining = 30000;
    private static boolean stopTimer = false;
    private Handler handler;
    //creates the ProblemActivity class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            //displays respective layout
            setContentView(R.layout.problem_layout);
            //creates timer
            stopTimer=false;
            //sets the time limit based on what was saved in the settings
            SharedPreferences save = getSharedPreferences("timeLimit", Context.MODE_PRIVATE);
            timeRemaining = save.getInt("time", 30000);
            //displays the timer in mm:ss form
            final TextView timer = (TextView)findViewById(R.id.timer);
            int sec = timeRemaining/1000;
            int min = timeRemaining/60000;
            sec = sec-min*60;
            String sSec = ""+sec;
            if(sSec.length()==1){
                sSec = "0"+sSec;
            }
            timer.setText(min+":"+sSec);
            //counts down the timer
            handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //subtracts a second to timer
                    timeRemaining = timeRemaining-1000;
                    //formats the timer
                    int seconds = (timeRemaining/1000);
                    int minutes = timeRemaining/60000;
                    seconds = seconds-minutes*60;
                    String stSec = ""+seconds;
                    if(stSec.length()==1){
                        stSec="0"+stSec;
                    }
                    timer.setText(minutes+":"+stSec);

                    //timer flashes when there is 10 seconds less
                    if(minutes<=0&&seconds<=10){
                        if(seconds%2==0){
                            timer.setBackgroundColor(Color.alpha(255));
                            timer.setTextColor(Color.rgb(244,67,54));
                        }
                        else{
                            timer.setTextColor(Color.WHITE);
                            timer.setBackgroundColor(Color.rgb(244,67,54));
                        }
                    }
                    //counts a second
                    if(timeRemaining>0&&!stopTimer){
                        handler.postDelayed(this,1000);
                    }
                    else if(timeRemaining==0){
                        timeUp();
                    }
                }
            };
            handler.postDelayed(runnable, 1000);

            //goes to the next question
            nextQuestion();

        }
    }

    //checks if back button was pressed and goes to home if pressed
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            stop();
        }
        return super.onKeyDown(keyCode, event);
    }

    //method to stop the timer from other classes
    public static void stop(){
        stopTimer=true;
    }

    //calls next question
    public void nextQuestion(){
        Bundle args = getIntent().getExtras();
        p = new Problem(args.getInt("index", 0));
        //changes textView to display next question
        TextView question = (TextView)findViewById(R.id.question);
        question.setText(Lessons.problemNum+") "+p.getQuestion());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        question.setPadding(padding, padding, padding, padding);

        //changes RadioButtons for next question
        answerChoices = (RadioGroup) findViewById(R.id.answerChoices);
        answerChoices.clearCheck();
        RadioButton[] answers = new RadioButton[5];
        for(int i=0; i<5; i++){
            answers[i] = (RadioButton)findViewById(ids[i]);
            answers[i].setText(p.getAnswers(i));
        }

        //changes the next button to submit for last question
        Button submit = (Button) findViewById(R.id.submit);
        if(Lessons.problemNum==19){
            submit.setText("SUBMIT");
        }
    }

    //changes id of radio buttons for easy checking
    public int changeID(int id){
        for(int i=0; i<5; i++){
            if(id==ids[i]) {
                return i;
            }
        }
        return -1;
    }

    //goes to scoreActivity when timer runs out
    public final void timeUp(){
        stop();
        Intent intent = new Intent();
        intent.setClass(this, ScoreActivity.class);
        startActivity(intent);
    }

    //checks which radio button was selected after the next button was pressed
    public void onClick(View view){
        int selected = answerChoices.getCheckedRadioButtonId();
        //adds how many problems was solved or correct
        if(p.checkAnswer(changeID(selected))) {
            Lessons.correct++;
            Lessons.problemNum++;
        }
        else{
            Lessons.problemNum++;
        }
        //goes to ScoreActivity after 20 problems
        if(Lessons.problemNum>=20){
            Intent intent = new Intent();
            intent.setClass(this, ScoreActivity.class);
            startActivity(intent);
        }
        else {
            nextQuestion();
        }
    }
}
