package com.github.yukihane.hazelcast.ch05;

import java.util.EventListener;

import com.hazelcast.core.MigrationEvent;
import com.hazelcast.core.MigrationListener;

public class MigrationStatusListener implements MigrationListener {

    @Override
    public void migrationStarted(final MigrationEvent migrationEvent) {
        MigrationStatus.migrationEvent(migrationEvent.getPartitionId(), true);
    }

    @Override
    public void migrationCompleted(final MigrationEvent migrationEvent) {
        MigrationStatus.migrationEvent(migrationEvent.getPartitionId(), false);
    }

    @Override
    public void migrationFailed(final MigrationEvent migrationEvent) {
        MigrationStatus.migrationEvent(migrationEvent.getPartitionId(), false);
    }

}
