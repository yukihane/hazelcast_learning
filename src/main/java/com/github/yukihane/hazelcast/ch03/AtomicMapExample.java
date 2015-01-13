package com.github.yukihane.hazelcast.ch03;

import java.util.Map;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class AtomicMapExample {

    public static void main(final String[] args) {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final Map<String, String> capitals = hz.getMap("capitals");

        capitals.putIfAbsent("GB", "Winchester");
        System.err.println("Capital of GB (until 1066): " + capitals.get("GB"));

        final String actualCapital = capitals.putIfAbsent("GB", "London");
        System.err.println("Failed to put as alreadty present: " + capitals.get("GB") + "=" + actualCapital);

        final boolean r1 = capitals.replace("GB", "Southampton", "London");
        System.err.println("Failed to replace as incorrect old value: " + capitals.get("GB") + "; [" + r1 + "]");

        final boolean r2 = capitals.replace("GB", "Winchester", "London");
        System.err.println("Capital of GB (since 1066): " + capitals.get("GB") + "; [" + r2 + "]");
    }
}
