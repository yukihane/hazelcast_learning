package com.github.yukihane.hazelcast.ch06;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.Callable;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;
import com.hazelcast.core.PartitionAware;
import com.hazelcast.monitor.LocalMapStats;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;

public class AverageCityPopulationCallable implements Callable<Integer>, HazelcastInstanceAware,
        PartitionAware<String>, Serializable {
    private static final long serialVersionUID = -1115619985567145380L;

    private final String country;
    private HazelcastInstance hz;

    public AverageCityPopulationCallable(final String country) {
        this.country = country;
    }

    @Override
    public String getPartitionKey() {
        return country;
    }

    @Override
    public void setHazelcastInstance(final HazelcastInstance hazelcastInstance) {
        this.hz = hazelcastInstance;
    }

    @Override
    public Integer call() throws Exception {
        System.err.println("Running task on: " + hz.getCluster().getLocalMember().toString());

        final IMap<String, City> cities = hz.getMap("cities");

        final LocalMapStats stats = cities.getLocalMapStats();
        System.err.println("Ownd entry count: " + stats.getOwnedEntryCount());

        final Predicate countryPredicate = Predicates.equal("country", country);
        final Collection<City> countryCities = cities.values(countryPredicate);

        int totalPopulation = 0;
        for (final City countryCity : countryCities) {
            totalPopulation += countryCity.getPopulation();
        }

        System.err.println("total: " + totalPopulation + ", size: " + countryCities.size());
        return totalPopulation / countryCities.size();
    }
}
