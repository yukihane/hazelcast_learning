package com.github.yukihane.hazelcast.ch03;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class TopicListener implements MessageListener<String> {

    @Override
    public void onMessage(final Message<String> message) {
        System.err.println("Received: " + message.getMessageObject());
    }

}
