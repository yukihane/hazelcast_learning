package com.github.yukihane.hazelcast.ch03;

import java.util.Date;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class MapLockingExample {

    public static void main(final String[] args) {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final IMap<String, Date> arrivals = hz.getMap("arrivals");

        if (arrivals.tryLock("London")) {
            try {
                arrivals.put("London", new Date());
            } finally {
                arrivals.unlock("London");
            }
        }
    }
}
