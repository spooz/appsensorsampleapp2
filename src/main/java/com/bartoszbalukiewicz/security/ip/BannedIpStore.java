package com.bartoszbalukiewicz.security.ip;

/**
 * Created by b.balukiewicz on 18.11.2016.
 */
public interface BannedIpStore {
    void ban(String ip);
    void unban(String ip);
    boolean isBanned(String ip);
}
