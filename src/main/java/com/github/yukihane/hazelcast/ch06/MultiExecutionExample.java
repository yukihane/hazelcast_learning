package com.github.yukihane.hazelcast.ch06;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.Member;

public class MultiExecutionExample {

    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final Config conf = new Config();
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

        final IExecutorService exec = hz.getExecutorService("exec");

        final TimeInstanceAwareCallable timeCallable = new TimeInstanceAwareCallable();

        while (true) {
            final Set<Member> clusterMembers = hz.getCluster().getMembers();

            final Map<Member, Future<String>> timeFutures = exec.submitToMembers(timeCallable, clusterMembers);

            for (final Future<String> timeFuture : timeFutures.values()) {
                final String theTime = timeFuture.get();
                System.err.println("The time is: " + theTime);
            }

            Thread.sleep(1000);
        }
    }
}
