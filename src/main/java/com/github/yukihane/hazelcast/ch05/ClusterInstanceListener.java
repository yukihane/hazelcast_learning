package com.github.yukihane.hazelcast.ch05;

import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.DistributedObjectListener;

public class ClusterInstanceListener implements DistributedObjectListener {

    @Override
    public void distributedObjectCreated(final DistributedObjectEvent event) {
        System.err.println("Created: " + event);
    }

    @Override
    public void distributedObjectDestroyed(final DistributedObjectEvent event) {
        System.err.println("Destroyed: " + event);
    }

}
