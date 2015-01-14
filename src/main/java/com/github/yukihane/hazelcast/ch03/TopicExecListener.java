package com.github.yukihane.hazelcast.ch03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class TopicExecListener implements MessageListener<String> {

    private final ExecutorService exec = Executors.newFixedThreadPool(10);

    @Override
    public void onMessage(final Message<String> message) {
        exec.execute(new Runnable() {

            @Override
            public void run() {
                System.err.println("Received: " + message.getMessageObject());
            }
        });
    }

}
