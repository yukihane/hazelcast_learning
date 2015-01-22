package com.github.yukihane.hazelcast.ch06;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Callable;

public class TimeCallable implements Callable<String>, Serializable {

    private static final long serialVersionUID = 3928938349870581095L;

    @Override
    public String call() throws Exception {
        return new Date().toString();
    }

}
