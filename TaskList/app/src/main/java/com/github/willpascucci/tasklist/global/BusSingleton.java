package com.github.willpascucci.tasklist.global;

import com.squareup.otto.Bus;

/**
 * Created by Nathan Walker on 2/4/15.
 */
public enum BusSingleton {
    INSTANCE;
    private Bus bus = new Bus();
    public static Bus get() {return INSTANCE.bus;}
}
