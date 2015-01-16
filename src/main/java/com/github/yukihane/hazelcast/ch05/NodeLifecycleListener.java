package com.github.yukihane.hazelcast.ch05;

import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

public class NodeLifecycleListener implements LifecycleListener {

    @Override
    public void stateChanged(final LifecycleEvent event) {
        System.err.println("stateChanged: " + event);
    }

}
