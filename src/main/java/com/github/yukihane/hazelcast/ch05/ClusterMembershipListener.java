package com.github.yukihane.hazelcast.ch05;

import com.hazelcast.core.MemberAttributeEvent;
import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;

public class ClusterMembershipListener implements MembershipListener {

    @Override
    public void memberAdded(final MembershipEvent membershipEvent) {
        System.err.println("Added: " + membershipEvent);
    }

    @Override
    public void memberRemoved(final MembershipEvent membershipEvent) {
        System.err.println("Removed: " + membershipEvent);
    }

    @Override
    public void memberAttributeChanged(final MemberAttributeEvent memberAttributeEvent) {
        System.err.println("Attribute changed: " + memberAttributeEvent);
    }

}
