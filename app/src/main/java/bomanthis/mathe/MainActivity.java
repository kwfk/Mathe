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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProblemActivity.stop();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.action_settings:
                //intent = new Intent();
                Intent setting = new Intent(this, SettingsActivity.class);
                this.startActivity(setting);
                return true;
            case R.id.action_help:
                Intent help = new Intent(this, HelpActivity.class);
                this.startActivity(help);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
