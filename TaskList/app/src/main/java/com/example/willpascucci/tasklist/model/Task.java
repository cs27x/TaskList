package com.example.willpascucci.tasklist.model;

import android.content.ClipData;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Nathan Walker on 2/4/15.
 */
@Table(name = "Tasks")
public class Task extends Model {
    @Column(name="Time")
    public Date time;

    @Column(name="Text")
    public String text;

    public Task() {
        super();
        time = new Date();
    }

    public Task(String text) {
        super();
        this.text = text;
    }
    public static List<Task> getAll() {
        return new Select()
                .from(Task.class)
                .orderBy("Time desc")
                .execute();
    }
}
