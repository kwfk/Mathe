package bomanthis.mathe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.util.Pools;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //creates the activity/page of the main home screen with all the lessons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //stops the timer so it doesn't go to the scoreActivity
        ProblemActivity.stop();

    }

    //creates the menu that houses the settings and help
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //checks which menu option was selected
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.action_settings:
                //calls the settings class
                Intent setting = new Intent(this, SettingsActivity.class);
                this.startActivity(setting);
                return true;
            case R.id.action_help:
                //calls the help class
                Intent help = new Intent(this, HelpActivity.class);
                this.startActivity(help);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
