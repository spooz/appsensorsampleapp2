package com.bartoszbalukiewicz.security.appsensor.response;

/**
 * Created by Bartek on 26.09.2016.
 */
public interface UserResponseCache {

    // mark user as being logged out
    public void setUserLoggedOut(String userName);

    // check if user is marked for logout
    public boolean isUserLoggedOut(String userName);

    // I logged the user out, so clear it so that next time, user is not denied access
    public void clearUserLoggedOut(String userName);

    // has logout been processed at least once
    public boolean isLogoutProcessed(String userName);

    // mark user as having had a logout processed
    public void processLogout(String userName);

}
