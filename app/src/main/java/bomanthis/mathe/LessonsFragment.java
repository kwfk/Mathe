package bomanthis.mathe;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LessonsFragment extends ListFragment {

    int selectedLesson = 0;
    //creates the listFragment to display the list of lessons
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> lessonNameToListView = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, Lessons.LESSONS);
        setListAdapter(lessonNameToListView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selLes", selectedLesson);
    }

    //calls showDetails with respective lesson details after checking which item was clicked
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
        Lessons.index = position;
        Lessons.problemNum = 0;
        Lessons.correct = 0;
    }
    //receives the details of which list item was clicked and goes to problemActivity and displays respective Lesson problems
    public void showDetails(int index){
        selectedLesson = index;
        Intent intent = new Intent();
        intent.setClass(getActivity(), ProblemActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void next(boolean n){
        if(n) showDetails(selectedLesson);
    }



}
