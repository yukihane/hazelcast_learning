package com.github.yukihane.hazelcast.ch07;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class VanillaInstanceExample {
    public static void main(final String[] args) {
        final Config conf = new Config();
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);
    }
}
