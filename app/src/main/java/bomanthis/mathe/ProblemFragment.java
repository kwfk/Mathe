package bomanthis.mathe;

import android.app.Fragment;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class ProblemFragment extends Fragment{

    public static ProblemFragment newInstance(int index){
        ProblemFragment f = new ProblemFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public int getShownIndex(){
        return getArguments().getInt("index", 0);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout qLayout = new LinearLayout(getActivity());
        qLayout.setOrientation(LinearLayout.VERTICAL);

        final Problem p = new Problem(getShownIndex());

        TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setTextSize(50);
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        qLayout.addView(text);
        text.setText(p.getQuestion());

        final RadioGroup answerChoices = new RadioGroup(getActivity());
        answerChoices.setPadding(80, 0, 0, 100);
        qLayout.addView(answerChoices);
        RadioButton[] answers = new RadioButton[5];
        for(int i=0; i<5; i++){
            answers[i] = new RadioButton(getActivity());
            answers[i].setText(p.getAnswers(i));
            answers[i].setTextSize(30);
            answers[i].setId(i);
            answerChoices.addView(answers[i]);
        }

        Button submit = new Button(getActivity());
        submit.setGravity(Gravity.CENTER);
        submit.setText("NEXT");
        submit.setTextSize(40);
        qLayout.addView(submit);
        submit.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    int selected = answerChoices.getCheckedRadioButtonId();
                    if(p.checkAnswer(selected)) {
                        Lessons.correct++;
                        Lessons.problemNum++;
                    }
                    else Lessons.problemNum++;
                    if(Lessons.problemNum>=20){
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), ScoreActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), ProblemActivity.class);
                        intent.putExtra("index", Lessons.index);
                        startActivity(intent);
                    }
                }
            }
        );


        return qLayout;
    }


}
