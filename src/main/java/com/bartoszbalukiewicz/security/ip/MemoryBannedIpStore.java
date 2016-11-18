package com.bartoszbalukiewicz.security.ip;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by b.balukiewicz on 18.11.2016.
 */
public class MemoryBannedIpStore implements BannedIpStore {

    private Set<String> store = new HashSet<>();

    @Override
    public void ban(String ip) {
        store.add(ip);
    }

    @Override
    public void unban(String ip) {
        store.remove(ip);
    }

    @Override
    public boolean isBanned(String ip) {
        return store.contains(ip);
    }
}
