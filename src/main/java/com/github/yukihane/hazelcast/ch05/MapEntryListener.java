package com.github.yukihane.hazelcast.ch05;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.MapEvent;

public class MapEntryListener implements EntryListener<String, String> {

    @Override
    public void entryAdded(final EntryEvent<String, String> event) {
        System.err.println("Added: " + event);
    }

    @Override
    public void entryRemoved(final EntryEvent<String, String> event) {
        System.err.println("Removed: " + event);
    }

    @Override
    public void entryUpdated(final EntryEvent<String, String> event) {
        System.err.println("Updated: " + event);
    }

    @Override
    public void entryEvicted(final EntryEvent<String, String> event) {
        System.err.println("Evicted: " + event);
    }

    @Override
    public void mapEvicted(final MapEvent event) {
        System.err.println("mapEvicted: " + event);
    }

    @Override
    public void mapCleared(final MapEvent event) {
        System.err.println("mapCleared: " + event);
    }

}
