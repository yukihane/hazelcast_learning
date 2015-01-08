package com.github.yukihane.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class SimpleMapExample {
    public static void main(final String[] args) {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final IMap<String, String> capitals = hz.<String, String> getMap("capitals");
        capitals.put("GB", "London");
        capitals.put("FR", "Paris");
        capitals.put("US", "Washington DC");
        capitals.put("AU", "Canberra");

        System.err.println("Known capital cities: " + capitals.size());
        System.err.println("Capital city of GB:" + capitals.get("GB"));
    }
}
