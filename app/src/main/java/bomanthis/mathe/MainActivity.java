package bomanthis.mathe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButton(View view){
        Button button = (Button) view;
        String tag = (String) button.getTag();

        if(tag.equals("lesson1")){
            Toast.makeText(getBaseContext(), "Addition and Subtraction", Toast.LENGTH_SHORT).show();
        }
        else if(tag.equals("lesson2"))
        {
            Toast.makeText(getBaseContext(), "Multiplication and Division", Toast.LENGTH_SHORT).show();
        }
        else if(tag.equals("lesson3")){
            Toast.makeText(getBaseContext(), "Trigonometry", Toast.LENGTH_SHORT).show();
        }
        else if(tag.equals("lesson4")){
            Toast.makeText(getBaseContext(), "Pre-Calculus", Toast.LENGTH_SHORT).show();
        }
        else if(tag.equals("lesson5")){
            Toast.makeText(getBaseContext(), "More", Toast.LENGTH_SHORT).show();
        }
        else if(tag.equals("lesson6")){

        }
    }
}
