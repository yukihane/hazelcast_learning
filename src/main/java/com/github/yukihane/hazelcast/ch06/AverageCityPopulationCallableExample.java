package com.github.yukihane.hazelcast.ch06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.IMap;

public class AverageCityPopulationCallableExample {
    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final Config conf = new Config();
        final MapConfig citiesConf = conf.getMapConfig("cities");
        citiesConf.addMapIndexConfig(new MapIndexConfig("country", false));

        final HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

        final IMap<String, City> cities = hz.getMap("cities");

        if (cities.isEmpty()) {
            cities.put("London-GB", new City("London", "GB", 8174100));
            cities.put("Southampton-GB", new City("Southampton", "GB", 304400));
            cities.put("Plymouth-GB", new City("Plymouth", "GB", 258700));
            cities.put("York-GB", new City("York", "GB", 197800));

            cities.put("Paris-FR", new City("Paris", "FR", 2268265));
        }
        final IExecutorService exec = hz.getExecutorService("exec");

        final Future<Integer> avgTask = exec.submit(new AverageCityPopulationCallable("GB"));

        final Integer avgPop = avgTask.get();
        System.err.println("Average GB city population: " + avgPop);

    }
}
