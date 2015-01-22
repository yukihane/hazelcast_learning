package com.github.yukihane.hazelcast.ch05;

import com.hazelcast.core.MigrationEvent;
import com.hazelcast.core.MigrationListener;

public class ClusterMigrationListener implements MigrationListener {

    @Override
    public void migrationStarted(final MigrationEvent migrationEvent) {
        System.err.println("Migration Started: " + migrationEvent);
    }

    @Override
    public void migrationCompleted(final MigrationEvent migrationEvent) {
        System.err.println("Migration Completed: " + migrationEvent);
    }

    @Override
    public void migrationFailed(final MigrationEvent migrationEvent) {
        System.err.println("Migration Failed: " + migrationEvent);
    }

}
