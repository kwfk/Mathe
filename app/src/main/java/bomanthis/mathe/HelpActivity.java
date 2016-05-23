package bomanthis.mathe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView help = new TextView(this);
        help.setTextSize(30);
        String text = "Change the way you practice math.";
        help.setText(text);
        help.setTextColor(Color.rgb(255,255,255));
        help.setBackgroundColor(Color.rgb(129,199,132));

        TextView i = new TextView(this);
        i.setText("Instructions");
        i.setGravity(Gravity.CENTER);
        i.setTextSize(25);

        TextView instructions = new TextView(this);
        instructions.setText("\t\t\tMathe helps you practice math under time pressure. " +
                "There are 5 different sections you can practice: " +
                "Addition and Subtraction, Multiplication and Division, Simple Algebra, Algebra I, and Trigonometry. " +
                "Tap the button corresponding to whichever level you want to practice, and just start answering questions. " +
                "Do as many questions out of 20 questions as you can in 30 seconds. " +
                "The timer at the top will tell you how much time is left, and it will start blinking when there is 10 seconds left. " +
                "You can see your progress in the settings.");
        instructions.setTextSize(18);


        layout.addView(help);
        layout.addView(i);
        layout.addView(instructions);
        setContentView(layout);
    }
}
