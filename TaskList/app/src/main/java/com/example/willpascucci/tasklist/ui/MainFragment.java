package com.example.willpascucci.tasklist.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.willpascucci.tasklist.R;
import com.example.willpascucci.tasklist.model.Task;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan Walker on 2/4/15.
 */
public class MainFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MainListAdapter mAdapter;
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
        List<Task> l = new ArrayList<>();
        mAdapter = new MainListAdapter(l);
        mRecyclerView.setAdapter(mAdapter);
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

    @Subscribe
    public void addTask(MainActivity.AddTaskEvent event) {
        mAdapter.taskList.add(new Task("New Task " + mAdapter.getItemCount()));
        mAdapter.notifyItemInserted(mAdapter.getItemCount());
    }

}
