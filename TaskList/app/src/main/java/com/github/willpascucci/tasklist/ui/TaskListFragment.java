package com.github.willpascucci.tasklist.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.willpascucci.tasklist.R;
import com.github.willpascucci.tasklist.model.TaskList;
import com.github.willpascucci.tasklist.model.TaskListAdapter;
import com.github.willpascucci.tasklist.model.Task;
import com.github.willpascucci.tasklist.global.BusSingleton;
import com.squareup.otto.Subscribe;

/**
 * Created by Nathan Walker on 2/4/15.
 */
public class TaskListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TaskListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.main_recycler_view);

        final EditText et = (EditText) rootView.findViewById(R.id.add_task);
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                    event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    BusSingleton.get().post(new AddTaskEvent(et.getText().toString()));
                    et.getText().clear();
                    return true;
                }
                return false;
            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TaskListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.taskList = Task.getOrdered();
        mAdapter.notifyDataSetChanged();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        BusSingleton.get().register(this);

    }

    @Override
    public void onStop() {
        BusSingleton.get().unregister(this);
        super.onStop();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public static class AddTaskEvent {
        public String text;

        public AddTaskEvent(String text) {
            this.text = text;
        }
    }
    @Subscribe
    public void addTask(AddTaskEvent event) {
        mAdapter.taskList.add(TaskList.newTask(event.text));
        mAdapter.notifyItemInserted(mAdapter.getItemCount());
    }

    public static class UpdateEvent { }

    @Subscribe
    public void updateList(UpdateEvent update) {
        mAdapter.taskList.clear();
        mAdapter.taskList.addAll(Task.getOrdered());
        mAdapter.notifyDataSetChanged();
    }

}
