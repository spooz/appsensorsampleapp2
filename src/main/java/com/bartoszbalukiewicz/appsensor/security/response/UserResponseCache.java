package com.bartoszbalukiewicz.appsensor.security.response;

/**
 * Created by postgres on 2016-09-27.
 */
/**
 * This is a simple cache interface that gets used by the
 * {@link com.bartoszbalukiewicz.appsensor.security.context.AppSensorSecurityContextRepository} in order to lookup the
 * state of a user with respect to logout.
 *
 *
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
