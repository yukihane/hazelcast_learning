package com.github.yukihane.hazelcast.ch02;

import java.util.concurrent.BlockingQueue;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class SimpleQueueExample {
    public static void main(final String[] args) throws InterruptedException {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final BlockingQueue<String> arrivals = hz.getQueue("arrivals");

        while (true) {
            final String arrival = arrivals.take();

            System.err.println("New arrival from: " + arrival);
        }
    }
}
