package com.github.willpascucci.tasklist.model;

/**
 * Created by willpascucci on 2/1/15.
 */

public class Event extends Task {

    private boolean skippable;

    public Event(int importance, String name, String description, String deadline,
                 String location, String category, double completed, boolean skippable) {
        super(importance, name, description, deadline, location, category, completed);
        this.skippable = skippable;
    }

    public boolean isSkippable() {
        return skippable;
    }

    public void setSkippable(boolean skippable) {
        this.skippable = skippable;
    }
}
