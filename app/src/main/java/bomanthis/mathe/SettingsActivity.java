package bomanthis.mathe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    History db;
    //creates teh settings activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        RadioGroup timeLimit = (RadioGroup) findViewById(R.id.setTimeLimit);

        //SharedPreference that stores how much time the timer should have
        SharedPreferences save = getSharedPreferences("timeLimit", Context.MODE_PRIVATE);
        //checks which time limit was selected before to display current setting
        switch(save.getInt("time", 30000)){
            case 30000:
                timeLimit.check(R.id.limit1);
                break;
            case 60000:
                timeLimit.check(R.id.limit2);
                break;
            case 120000:
                timeLimit.check(R.id.limit3);
                break;
        }

        //calls the clearHistory button
        Button clearHistory = (Button)findViewById(R.id.clearHistoryButton);

        //calls TableLayout
        TableLayout table = (TableLayout)findViewById(R.id.table);
        db = new History(this);
        Cursor res = db.getAllData();
        res.moveToLast();
        if(res.getCount()!=0) {
            do{
                //adds a table row for the new history data
                TableRow tr = new TableRow(this);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                //adds history dates
                TextView data1 = new TextView(this);
                String date = res.getString(1);
                data1.setText(date);
                data1.setPadding(10, 0, 0, 0);
                data1.setTextSize(18);
                tr.addView(data1);

                //adds history lesson name
                TextView data2 = new TextView(this);
                String lesson = res.getString(2);
                data2.setText(lesson);
                data2.setPadding(80, 0, 0, 0);
                data2.setTextSize(18);
                tr.addView(data2);

                //adds history problem solved
                TextView data3 = new TextView(this);
                String prob = res.getString(3);
                data3.setText(prob);
                data3.setPadding(80, 0, 0, 0);
                data3.setTextSize(18);
                tr.addView(data3);

                //adds history score
                TextView data4 = new TextView(this);
                String score = res.getString(4);
                data4.setText(score);
                data4.setPadding(80, 0, 0, 0);
                data4.setTextSize(18);
                tr.addView(data4);

                table.addView(tr);

            }while(res.moveToPrevious());
        }
    }

    //checks if the radio buttons to change the time limit is changed and changes the time limit
    public void onTimeClick(View view) {
        boolean checked = ((RadioButton)view).isChecked();
        SharedPreferences save = getSharedPreferences("timeLimit", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        switch (view.getId()){
            case R.id.limit1:
                if(checked){
                    editor.putInt("time", 30000);
                    editor.apply();
                }
                break;
            case R.id.limit2:
                if(checked){
                    editor.putInt("time", 60000);
                    editor.apply();
                }
                break;
            case R.id.limit3:
                if(checked){
                    editor.putInt("time", 120000);
                    editor.apply();
                }
                break;
        }
    }

    //checks if button to clear history was clicked and clears history if clicked
    public void onClick(View view) {
        Integer deletedRows = db.clear();
        Intent intent = new Intent(this, this.getClass());
        startActivity(intent);
        if(deletedRows>0){
            Toast.makeText(SettingsActivity.this, "History Cleared", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(SettingsActivity.this, "History Not Cleared", Toast.LENGTH_LONG).show();
    }
}
