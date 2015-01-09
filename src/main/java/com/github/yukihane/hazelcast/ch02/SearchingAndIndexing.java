package com.github.yukihane.hazelcast.ch02;

import java.util.Collection;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;
import com.hazelcast.query.SqlPredicate;

public class SearchingAndIndexing {

    public static void main(final String[] args) {
        final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        final IMap<String, City> capitals = hz.getMap("capitals");
        capitals.addIndex("name", false);
        capitals.addIndex("population", true);

        capitals.put("GB", new City("London", "GB", 8174100));
        capitals.put("FR", new City("Paris", "FR", 2268265));
        capitals.put("US", new City("Washington DC", "US", 601723));
        capitals.put("AU", new City("Canberra", "AU", 354644));

        Collection<City> possibleLondons = capitals.values(new SqlPredicate("name = 'London'"));
        System.err.println(possibleLondons);

        Collection<City> largeCities = capitals.values(new SqlPredicate("population > 1000000"));
        System.err.println(largeCities);

        final EntryObject c = new PredicateBuilder().getEntryObject();
        final Predicate londonPredicate = c.get("name").equal("London");
        possibleLondons = capitals.values(londonPredicate);
        System.err.println(possibleLondons);

        final Predicate largeCityPredicate = Predicates.greaterThan("population", Integer.valueOf(1000000));
        largeCities = capitals.values(largeCityPredicate);
        System.err.println(largeCities);
    }

}
