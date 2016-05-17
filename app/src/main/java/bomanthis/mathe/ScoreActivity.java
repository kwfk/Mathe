package bomanthis.mathe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        TextView s = (TextView) findViewById(R.id.score);
        String score = Lessons.correct + "/" + Lessons.problemNum;
        //String percent = "" + (int)((double)(Lessons.correct / Lessons.problemNum) * 100)+"%";
        //System.out.println(score +" "+percent);
        s.setText(score);
        //TextView p = (TextView) findViewById(R.id.percent);
        //p.setText(percent);


        //setContentView(R.layout.score_layout);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("backHome", "ScoreActivity");
        startActivity(intent);
    }
}
