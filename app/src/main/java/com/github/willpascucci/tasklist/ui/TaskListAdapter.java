package com.github.willpascucci.tasklist.ui;


import android.app.DialogFragment;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.willpascucci.tasklist.R;
import com.github.willpascucci.tasklist.global.BusSingleton;
import com.github.willpascucci.tasklist.model.Task;

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
        //public ImageButton pauseButton;
        public Chronometer chrono;
        public ImageButton startButton;
        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.textView);
            removeButton = (ImageButton) v.findViewById(R.id.button_close);
            //pauseButton = (ImageButton) v.findViewById(R.id.button_pause);
            chrono = (Chronometer) v.findViewById(R.id.chrono);
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
        holder.text.setText(task.text);

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = taskList.indexOf(task);
                taskList.remove(index);
                notifyItemRemoved(index);
                task.delete();
            }
        });

        /*
        holder.pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = taskList.indexOf(task);
                taskList.get(index).pauseTask();
            }
        });
        */
        final ViewHolder finalHolder = holder;

        holder.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (task.isWorking()) {
                    task.pauseTask();
                    ((ImageButton)v).setImageDrawable(v.getContext().getResources().getDrawable(R.drawable.ic_av_play_arrow));
                    finalHolder.chrono.stop();
                } else {
                    task.startTask();
                    ((ImageButton) v).setImageDrawable(v.getContext().getResources().getDrawable(R.drawable.ic_av_pause));
                    finalHolder.chrono.setBase(SystemClock.elapsedRealtime()-task.getElapsedTime());
                    finalHolder.chrono.start();
                }
            }
        });

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusSingleton.get().post(new TaskListActivity.EditTaskEvent(task));
            }
        });

    }

    public int getItemCount() {
        return taskList.size();
    }

}
