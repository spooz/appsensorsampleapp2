package com.bartoszbalukiewicz.appsensor.geolocation;

import com.maxmind.geoip2.record.Country;
import org.owasp.appsensor.core.geolocation.GeoLocator;

import java.net.InetAddress;

/**
 * Created by Bartek on 20.11.2016.
 */
public interface CustomGeoLocator extends GeoLocator {

    Country getCountry(InetAddress address);
    Country getCountry(String address);

}
