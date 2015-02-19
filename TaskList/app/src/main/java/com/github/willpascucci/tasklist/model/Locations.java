package com.github.willpascucci.tasklist.model;

/**
 * Created by willpascucci on 2/1/15.
 */

import java.util.ArrayList;

public class Locations {

    private ArrayList<String> locations;

    public Locations() {
    }
    
    public Locations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }
}
