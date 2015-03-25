package com.github.willpascucci.tasklist.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.github.willpascucci.tasklist.R;
import com.github.willpascucci.tasklist.global.BusSingleton;
import com.github.willpascucci.tasklist.model.Task;
import com.squareup.otto.Subscribe;


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
    protected void onStart() {
        super.onStart();
        BusSingleton.get().register(this);
    }

    @Override
    protected void onStop() {
        BusSingleton.get().unregister(this);
        super.onStop();
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
                BusSingleton.get().post(new TaskListFragment.AddTaskEvent(""));
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public static class EditTaskEvent {
        public Task task;

        public EditTaskEvent(Task task) {
            this.task = task;
        }
    }


    @Subscribe
    public void launchEditDialog(EditTaskEvent e) {
        DialogFragment etd = EditTaskDialog.newInstance(e.task.getId());
        etd.show(getFragmentManager(), "TAG_EDIT_TASK");
    }
}


