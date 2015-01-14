package com.github.yukihane.hazelcast.ch03;

import java.util.Date;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

public class TopicExample {

    public static void main(final String[] args) throws InterruptedException {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final ITopic<String> broadcastTopic = hz.getTopic("broadcast");
        broadcastTopic.addMessageListener(new TopicListener());

        while (true) {
            broadcastTopic.publish(new Date() + "-" + hz.getCluster().getLocalMember() + " says hello");
            Thread.sleep(1000);
        }
    }
}
