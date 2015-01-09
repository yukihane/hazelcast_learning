package com.github.yukihane.hazelcast.ch02;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ISet;

public class SimpleMapExample {
    public static void main(final String[] args) {

        // Sec. Mapping back to the real world

        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final IMap<String, String> capitals = hz.getMap("capitals");
        capitals.put("GB", "London");
        capitals.put("FR", "Paris");
        capitals.put("US", "Washington DC");
        capitals.put("AU", "Canberra");

        System.err.println("Known capital cities: " + capitals.size());
        System.err.println("Capital city of GB:" + capitals.get("GB"));

        // Sec. Sets, lists, and queues

        final ISet<String> cities = hz.getSet("cities");
        cities.addAll(capitals.values());
        cities.add("London");
        cities.add("Rome");
        cities.add("New York");

        final IList<String> countries = hz.getList("countries");
        countries.addAll(capitals.keySet());
        countries.add("CA");
        countries.add("DE");
        countries.add("GB");
    }
}
