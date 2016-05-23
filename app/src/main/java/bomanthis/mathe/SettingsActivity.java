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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        RadioGroup timeLimit = (RadioGroup) findViewById(R.id.setTimeLimit);
        //RadioButton thirtySec = (RadioButton)findViewById(R.id.limit1);
        //RadioButton oneMinute = (RadioButton)findViewById(R.id.limit2);
        //RadioButton twoMinutes = (RadioButton)findViewById(R.id.limit3);
        SharedPreferences save = getSharedPreferences("timeLimit", Context.MODE_PRIVATE);
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


        Button clearHistory = (Button)findViewById(R.id.clearHistoryButton);

        display();
        /*StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Date :"+res.getString(0)+"\n");
            buffer.append("Problem Solved :"+res.getString(1)+"\n");
            buffer.append("Score :"+res.getString(2)+"\n");
        }*/

        //showMessage("Data", buffer.toString());
    }

    /*public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }*/

    public void display(){
        TableLayout table = (TableLayout)findViewById(R.id.table);
        db = new History(this);
        Cursor res = db.getAllData();
        res.moveToLast();
        if(res.getCount()!=0) {
            do{
                TableRow tr = new TableRow(this);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView data1 = new TextView(this);
                String date = res.getString(1);
                data1.setText(date);
                data1.setPadding(10, 0, 0, 0);
                data1.setTextSize(18);
                tr.addView(data1);

                TextView data2 = new TextView(this);
                Toast.makeText(SettingsActivity.this, res.getString(2), Toast.LENGTH_SHORT).show();
                String lesson = res.getString(2);
                data2.setText(lesson);
                data2.setPadding(80, 0, 0, 0);
                data2.setTextSize(18);
                tr.addView(data2);

                TextView data3 = new TextView(this);
                String prob = res.getString(3);
                data3.setText(prob);
                data3.setPadding(80, 0, 0, 0);
                data3.setTextSize(18);
                tr.addView(data3);

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

    public void onTimeClick(View view) {
        boolean checked = ((RadioButton)view).isChecked();
        SharedPreferences save = getSharedPreferences("timeLimit", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        switch (view.getId()){
            case R.id.limit1:
                if(checked){
                    editor.putInt("time", 30000);
                    editor.apply();
                    //Lessons.timeLimit=30000;
                }
                break;
            case R.id.limit2:
                if(checked){
                    editor.putInt("time", 60000);
                    editor.apply();
                    //Lessons.timeLimit=60000;
                }
                break;
            case R.id.limit3:
                if(checked){
                    editor.putInt("time", 120000);
                    editor.apply();
                    //Lessons.timeLimit=120000;
                }
                break;
        }
    }

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
