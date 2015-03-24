package com.github.willpascucci.tasklist.model;

import com.activeandroid.ActiveAndroid;

import java.util.Date;
import java.util.List;

/**
 * Created by Nathan Walker on 3/18/15.
 */
public class TaskList {
    public static Task newTask() {
        Task task = new Task(null);
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
            float timeRemaining = t.timeRequired - t.elapsedTime;
            if (timeRemaining < 0) timeRemaining = 0;
            t.priority = t.importance - timeRemaining / 86400;
            t.save();
        }
        ActiveAndroid.endTransaction();
    }

    public static void updatePriority(int id) {
        ActiveAndroid.beginTransaction();
        Task t = Task.get(id);
        float timeRemaining = t.timeRequired - t.elapsedTime;
            if (timeRemaining < 0) timeRemaining = 0;
            t.priority = t.importance - timeRemaining / 86400;
            t.save();
    }

}
