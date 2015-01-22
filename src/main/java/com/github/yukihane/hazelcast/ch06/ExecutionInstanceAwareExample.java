package com.github.yukihane.hazelcast.ch06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class ExecutionInstanceAwareExample {
    public static void main(final String[] args) throws Exception {
        final Config conf = new Config();
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

        final ExecutorService exec = hz.getExecutorService("exec");

        while (true) {
            final Future<String> timeFuture = exec.submit(new TimeInstanceAwareCallable());
            final String theTime = timeFuture.get();

            System.err.println("The time is: " + theTime);

            Thread.sleep(1000);
        }
    }
}
