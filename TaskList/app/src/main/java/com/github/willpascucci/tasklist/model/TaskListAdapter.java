package com.github.willpascucci.tasklist.model;


import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.willpascucci.tasklist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan Walker on 2/4/15.
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    public List<Task> taskList;
    private ViewHolder mHolder;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageButton removeButton;
        public ImageButton pauseButton;
        public ImageButton startButton;
        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.textView);
            removeButton = (ImageButton) v.findViewById(R.id.button_close);
            pauseButton = (ImageButton) v.findViewById(R.id.button_pause);
            startButton = (ImageButton) v.findViewById(R.id.button_play);
        }
    }

    public TaskListAdapter() {
        taskList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_list_item, null);

        mHolder = new ViewHolder(v);
        return mHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        final Task task = taskList.get(i);
        holder.text.setHint("New Task " + i);
        holder.text.setText(task.text);
        holder.text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                task.text = s.toString();
                task.save();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = taskList.indexOf(task);
                taskList.remove(index);
                notifyItemRemoved(index);
                task.delete();
            }
        });

        holder.pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = taskList.indexOf(task);
                taskList.get(index).pauseTask();
            }
        });

        holder.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = taskList.indexOf(task);
                taskList.get(index).startTask();
            }
        });
    }

    public int getItemCount() {
        return taskList.size();
    }
}
