package com.github.yukihane.hazelcast.ch06;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Callable;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

public class TimeInstanceAwareCallable implements Callable<String>, HazelcastInstanceAware, Serializable {

    private static final long serialVersionUID = -1091136007918147862L;

    private HazelcastInstance hz;

    @Override
    public void setHazelcastInstance(final HazelcastInstance hazelcastInstance) {
        this.hz = hazelcastInstance;
    }

    @Override
    public String call() throws Exception {
        return hz.getCluster().getLocalMember().toString() + " - " + new Date().toString();
    }

}
