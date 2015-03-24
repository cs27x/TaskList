package com.github.willpascucci.tasklist.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TaskListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.taskList = Task.getAll();
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

    @Subscribe
    public void addTask(TaskListActivity.AddTaskEvent event) {
        mAdapter.taskList.add(TaskList.newTask(null));
        mAdapter.notifyItemInserted(mAdapter.getItemCount());
    }

}
