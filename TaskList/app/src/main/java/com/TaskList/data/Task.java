package com.TaskList.data;

/**
 * Created by willpascucci on 1/29/15.
 */

import java.util.Date;
import java.text.SimpleDateFormat;

public class Task {

    protected int importance;
    protected String name;
    protected String description;
    protected String deadline;
    protected String location;
    protected String category;
    protected double completed;

    public Task() {

    }

    public Task(int importance, String name, String description, String deadline,
                            String location, String category, double completed) {
        this.importance = importance;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.location = location;
        this.category = category;
        this.completed = completed;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


}
