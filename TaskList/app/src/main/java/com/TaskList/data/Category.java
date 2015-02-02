package com.TaskList.data;

/**
 * Created by willpascucci on 2/1/15.
 */

import java.util.ArrayList;
import java.util.Date;

public class Category {

    private String name;
    private ArrayList<Task> items;
    private int importance;
    private double utility;
    private double efficient_time;

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public double getUtility() {
        return utility;
    }

    public void setUtility(double utility) {
        this.utility = utility;
    }

    public double getEfficient_time() {
        return efficient_time;
    }

    public void setEfficient_time(double efficient_time) {
        this.efficient_time = efficient_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Task> getItems() {
        return items;
    }

    public void setItems(ArrayList<Task> items) {
        this.items = items;
    }
}
