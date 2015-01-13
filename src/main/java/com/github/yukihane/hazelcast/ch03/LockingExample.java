package com.github.yukihane.hazelcast.ch03;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;

/**
 * 複数の端末から
 * <code>mvn exec:java -Dexec.mainClass=com.github.yukiha.hazelcast.ch03.LockingExample</code>
 * コマンドを実行することでロックの確認ができます.
 */
public class LockingExample {

    public static void main(final String[] args) throws InterruptedException {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final ILock lock = hz.getLock("theTime");

        while (true) {
            if (lock.tryLock(30, TimeUnit.SECONDS)) {
                try {
                    while (true) {
                        System.err.println(new Date());
                        Thread.sleep(1000);
                    }
                } finally {
                    lock.unlock();
                }
            }

        }
    }
}
