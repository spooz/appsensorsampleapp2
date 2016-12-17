package com.bartoszbalukiewicz.appsensor.geolocation;

import com.google.common.net.InetAddresses;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import org.owasp.appsensor.core.geolocation.GeoLocation;
import org.owasp.appsensor.core.geolocation.GeoLocator;
import org.owasp.appsensor.core.logging.Loggable;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by Bartek on 19.11.2016.
 */
@Component
@Loggable
public class CustomGeoLocatorImpl implements CustomGeoLocator {

    private Logger logger;
    private DatabaseReader reader;

    @Value("${appsensor.geoip.file}")
    private String geoIpFile;

    @Override
    public Country getCountry(InetAddress address) {
        if(reader == null) {
            this.initializeDataset();
        }

        Country c = null;

        try {
            if(reader != null) {
               c = reader.city(address).getCountry();
            }
        } catch (GeoIp2Exception | IOException var4) {
            if(this.logger != null) {
                this.logger.warn("Couldn\'t locate lat/long for address", var4);
            }
        }

        return c;
    }

    @Override
    public Country getCountry(String address) {
        return getCountry(InetAddresses.forString(address));
    }

    @Override
    public GeoLocation readLocation(InetAddress address) {
        if(reader == null) {
            this.initializeDataset();
        }

        GeoLocation geoLocation = null;

            try {
                if(reader != null) {
                    Location e = reader.city(address).getLocation();
                    geoLocation = new GeoLocation(e.getLatitude().doubleValue(), e.getLongitude().doubleValue());
                }
            } catch (GeoIp2Exception | IOException var4) {
                if(this.logger != null) {
                    this.logger.warn("Couldn\'t locate lat/long for address", var4);
                }
        }

        return geoLocation;
    }

    private void initializeDataset() {

            this.logger.info("Geolocation enabled: attempting to load database from " + geoIpFile);

            try {
                File e = new File(geoIpFile);
                reader = (new DatabaseReader.Builder(e)).build();
            } catch (IOException var2) {
                if(this.logger != null) {
                    this.logger.warn("Couldn\'t load IP address <--> geolocation DB", var2);
                }
            }


    }


}
