package com.github.willpascucci.tasklist.model;

import com.activeandroid.ActiveAndroid;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

/**
 * Created by Nathan Walker on 3/18/15.
 */
public class TaskList {
    public static Task newTask(String s) {
        Task task = new Task(s);
        task.save();
        return task;
    }

    public static void start(int id) {
        ActiveAndroid.beginTransaction();
        Task t = Task.get(id);
        t.startTask();
        ActiveAndroid.endTransaction();
    }

    public static void pause(int id) {
        ActiveAndroid.beginTransaction();
        Task.get(id).pauseTask();
        ActiveAndroid.endTransaction();
    }

    public static void defer(int task) {
    }

    public static void updateAllPriorities() {
        ActiveAndroid.beginTransaction();
        for (Task t : Task.getAll()) {
            setPriority(t);                                         //edit by Sahil
        }
        ActiveAndroid.endTransaction();
    }

    public static void updatePriority(long id) {
        ActiveAndroid.beginTransaction();
        Task t = Task.get(id);
        setPriority(t);                                             //edit by Sahil
        ActiveAndroid.endTransaction();                             //edit by Sahil
    }

    // added by Sahil

    public static void setPriority(Task t1){
        final Calendar c = Calendar.getInstance();
        t1.timeRequired = (float) t1.getDeadline().getTime()-c.getTime().getTime();
        t1.elapsedTime = (long) c.getTime().getTime()-t1.getTime().getTime();
        float timeRemaining = t1.timeRequired - t1.elapsedTime;
        if (timeRemaining < 0) timeRemaining = 0.0f;
        t1.priority = t1.importance * 10;                             //get a score out of 100
        if ((timeRemaining/t1.timeRequired) < 0.5){                         //less than half time left
            if ((timeRemaining/t1.timeRequired) < 0.75) t1.priority += 40;   //less than quarter time left, increase priority
            else t1.priority += 20;                                          //increase priority by a bit less for half time
        }
        t1.save();
    }

}
