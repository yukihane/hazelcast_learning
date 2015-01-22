package com.github.yukihane.hazelcast.ch06;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;

public class MemberInfoStatsExample {

    public static void main(final String[] args) {
        final Config conf = new Config();
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

        final IExecutorService exec = hz.getExecutorService("exec");

        // DistributedMemberInfoCallable はv3.4には存在しないが, 代替を発見できず
    }
}
