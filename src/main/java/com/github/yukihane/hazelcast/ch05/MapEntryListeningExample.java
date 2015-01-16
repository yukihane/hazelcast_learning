package com.github.yukihane.hazelcast.ch05;

import java.util.concurrent.TimeUnit;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class MapEntryListeningExample {
    public static void main(final String[] args) {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final IMap<String, String> capitals = hz.getMap("capitals");
        capitals.addEntryListener(new MapEntryListener(), true);

        capitals.put("GB", "Winchester");
        capitals.put("GB", "London");
        capitals.put("DE", "Berlin", 10, TimeUnit.SECONDS);
        capitals.remove("GB");
    }
}
