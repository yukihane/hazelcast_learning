package com.github.yukihane.hazelcast.ch05;

import com.hazelcast.config.Config;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class ClusterListeningExample {
    public static void main(final String[] args) {
        final Config config = new Config();
        config.addListenerConfig(new ListenerConfig(new ClusterInstanceListener()));
        config.addListenerConfig(new ListenerConfig(new ClusterMembershipListener()));
        config.addListenerConfig(new ListenerConfig(new NodeLifecycleListener()));

        final HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
    }
}
