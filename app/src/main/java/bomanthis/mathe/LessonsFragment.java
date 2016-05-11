package bomanthis.mathe;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LessonsFragment extends ListFragment {

    int selectedLesson = 0;
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

    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }
    void showDetails(int index){
        selectedLesson = index;
        Intent intent = new Intent();
        intent.setClass(getActivity(), ProblemActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }
}
