package com.github.yukihane.hazelcast.ch06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class ExecutionExample {

    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final Config conf = new Config();
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

        final ExecutorService exec = hz.getExecutorService("exec");

        while (true) {
            final Future<String> timeFuture = exec.submit(new TimeCallable());
            final String theTime = timeFuture.get();

            System.err.println(theTime);

            Thread.sleep(1000);
        }
    }
}
