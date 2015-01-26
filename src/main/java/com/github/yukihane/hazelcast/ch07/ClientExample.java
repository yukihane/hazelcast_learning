package com.github.yukihane.hazelcast.ch07;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class ClientExample {

    public static void main(final String[] args) {
        final ClientConfig conf = new ClientConfig();
        // IPアドレスは私の独自環境に依存したものです(書籍記載とは異なります).
        conf.getNetworkConfig().addAddress("192.168.122.1:5701");

        final HazelcastInstance hzc = HazelcastClient.newHazelcastClient(conf);

        final IMap<String, String> capitals = hzc.getMap("capitals");

        if (capitals.isEmpty()) {
            System.err.println("Empty capitals map, adding entries");

            capitals.put("GB", "London");
            capitals.put("FR", "Paris");
            capitals.put("US", "Washington DC");
            capitals.put("AU", "Canberra");
        }

        System.err.println("Known capital cities: " + capitals.size());

        System.err.println("Capital city of GB: " + capitals.get("GB"));

        hzc.shutdown();
    }
}
