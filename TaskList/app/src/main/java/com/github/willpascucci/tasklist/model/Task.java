package com.github.willpascucci.tasklist.model;

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
    @Column(name="Time")        public Date time;
    @Column(name="Text")        public String text;
    @Column(name="Importance")  public int importance;
    @Column(name="Description") public String description;
    @Column(name="Deadline")    public String deadline;
    @Column(name="Location")    public String location;
    @Column(name="Category")    public String category;
    @Column(name="Completed")   public double completed;

    public Task() {
        super();
        time = new Date();
    }

    public Task(String text) {
        super();
        this.text = text;
        this.importance = 0;
        this.deadline = null;
        this.location = null;
        this.category = null;
        this.completed = 0;
    }

    public Task(int importance, String text, String description, String deadline,
                String location, String category, double completed) {
        this.importance = importance;
        this.text = text;
        this.description = description;
        this.deadline = deadline;
        this.location = location;
        this.category = category;
        this.completed = completed;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static List<Task> getAll() {
        return new Select()
                .from(Task.class)
                .orderBy("Time desc")
                .execute();
    }
}
