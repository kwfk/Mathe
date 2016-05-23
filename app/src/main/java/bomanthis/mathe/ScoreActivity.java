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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        TextView solved = (TextView)findViewById(R.id.solved);
        solved.setText("Problems solved: "+Lessons.problemNum);
        TextView score = (TextView) findViewById(R.id.score);
        int percent = (int)((double)Lessons.correct/20*100);
        //Toast.makeText(ScoreActivity.this, ""+percent, Toast.LENGTH_SHORT).show();

        //percent = Math.round(percent)/100;
        String fracScore = Lessons.correct+"/"+20;
        String detail = fracScore+"\n" + percent+"% \n";
        //String percent = "" + (int)((double)(Lessons.correct / Lessons.problemNum) * 100)+"%";
        //System.out.println(score +" "+percent);
        score.setText(detail);
        //TextView p = (TextView) findViewById(R.id.percent);
        //p.setText(percent);
        his = new History(this);
        Calendar c = Calendar.getInstance();
        //String time = ""+c.getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yy HH:mm");
        String date = df.format(c.getTime());
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
        his.insertData(date, lesson, ""+Lessons.problemNum, fracScore);

        //setContentView(R.layout.score_layout);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("backHome", "ScoreActivity");
        startActivity(intent);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
