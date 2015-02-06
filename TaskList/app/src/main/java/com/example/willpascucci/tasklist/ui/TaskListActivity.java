package com.example.willpascucci.tasklist.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.willpascucci.tasklist.R;
import com.example.willpascucci.tasklist.global.BusSingleton;


public class TaskListActivity extends BaseActivity {
    private static final String TAG = TaskListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFinishing()) return;

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new TaskListFragment(), "main").commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case (R.id.action_settings):
                return true;
            case (R.id.action_add):
                BusSingleton.get().post(new AddTaskEvent());
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public static class AddTaskEvent {}
}


