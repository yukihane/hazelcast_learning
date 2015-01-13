package com.github.yukihane.hazelcast.ch03;

import com.hazelcast.core.BaseMap;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.transaction.TransactionContext;
import com.hazelcast.transaction.TransactionOptions;
import com.hazelcast.transaction.TransactionOptions.TransactionType;

/**
 * <a href=
 * "http://docs.hazelcast.org/docs/3.4/manual/html-single/hazelcast-documentation.html#transaction-interface"
 * >マニュアル</a> を見て改変しています.
 */
public class TransactionExample {

    public static void main(final String[] args) {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final TransactionOptions options = new TransactionOptions().setTransactionType(TransactionType.LOCAL);
        final TransactionContext txc = hz.newTransactionContext(options);

        txc.beginTransaction();
        final BaseMap<String, String> testMap = txc.getMap("test");

        try {
            System.err.println(testMap.get("foo"));

            Thread.sleep(10000);

            System.err.println(testMap.get("foo"));
            testMap.put("foo", "bar");

            Thread.sleep(10000);

            txc.commitTransaction();
        } catch (final Exception e) {
            e.printStackTrace();
            txc.rollbackTransaction();
        }

        final IMap<String, String> res = hz.getMap("test");
        System.err.println(res.get("foo"));
    }
}
