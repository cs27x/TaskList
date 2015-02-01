package com.TaskList.data;

/**
 * Created by willpascucci on 2/1/15.
 */

import java.util.ArrayList;

public class Category {

    private String name;
    private ArrayList<Task> items;

    public Category(String name, ArrayList<Task> items) {
        this.name = name;
        this.items = items;
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
