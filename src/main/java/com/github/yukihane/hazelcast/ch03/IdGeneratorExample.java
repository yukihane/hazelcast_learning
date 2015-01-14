package com.github.yukihane.hazelcast.ch03;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;

public class IdGeneratorExample {

    public static void main(final String[] args) throws InterruptedException {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final IdGenerator idGen = hz.getIdGenerator("newId");

        while (true) {
            final long id = idGen.newId();
            System.err.println("New Id: " + id);
            Thread.sleep(10);
        }
    }

}
