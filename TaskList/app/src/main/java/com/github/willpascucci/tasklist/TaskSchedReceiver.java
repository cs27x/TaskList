package com.github.willpascucci.tasklist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.github.willpascucci.tasklist.model.Task;

/**
 * Created by Nathan Walker on 3/11/15.
 */
public class TaskSchedReceiver extends BroadcastReceiver{
    private static final String EXTRA_TASK = "EXTRA_TASK";
    private static final String TAG = TaskSchedReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra(EXTRA_TASK, -1);
        if (id == -1) {
            Log.d(TAG, "error: no task id passed to receiver");
            return;
        } else {
            Task task = Task.get(id);
        }
        // TODO launch notification / interact with user
    }

    public static PendingIntent scheduleTask(Context context, Task task) {
        AlarmManager alm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, TaskSchedReceiver.class);
        intent.putExtra(EXTRA_TASK, task.getId());

        PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, 0);
        alm.set(AlarmManager.RTC, task.getDeadline().getTime(), pending);

        return pending;
    }

}
