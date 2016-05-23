package bomanthis.mathe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.style.TtsSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScoreActivity extends AppCompatActivity {


    History his;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creates the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);

        //calls the textView for displaying the number of problems solved
        TextView solved = (TextView)findViewById(R.id.solved);
        solved.setText("Problems solved: "+Lessons.problemNum);
        //calls the textView for displaying the score
        TextView score = (TextView) findViewById(R.id.score);
        //calculates the percentage
        int percent = (int)((double)Lessons.correct/20*100);
        //sets string to display the complete score
        String fracScore = Lessons.correct+"/"+20;
        String detail = fracScore+"\n" + percent+"% \n";
        score.setText(detail);
        //calls the history SQL database
        his = new History(this);
        //sets the string for the date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yy HH:mm");
        String date = df.format(c.getTime());
        //sets the string for the lesson
        String lesson = "";
        switch(Lessons.index){
            case 0:
                lesson = "A&S";
                break;
            case 1:
                lesson = "M&D";
                break;
            case 2:
                lesson = "S.Alg";
                break;
            case 3:
                lesson="Alg1";
                break;
            case 4:
                lesson = "Trig";
                break;
        }
        //inserts the data for history into SQL database
        his.insertData(date, lesson, ""+Lessons.problemNum, fracScore);
    }

    //checks if button to back to home is clicked and starts activity if clicked
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //checks if back button was pressed and goes back to home if pressed
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
