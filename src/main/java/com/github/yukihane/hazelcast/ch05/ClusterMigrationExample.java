package com.github.yukihane.hazelcast.ch05;

import com.hazelcast.config.Config;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class ClusterMigrationExample {

    public static void main(final String[] args) throws InterruptedException {
        final Config conf = new Config();
        conf.addListenerConfig(new ListenerConfig(new ClusterMembershipListener()));
        conf.addListenerConfig(new ListenerConfig(new MigrationStatusListener()));

        final HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

        while (true) {
            System.err.println("Is Migrating?: " + MigrationStatus.isMigrationg());
            Thread.sleep(5000);
        }
    }
}
