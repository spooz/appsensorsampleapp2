package com.bartoszbalukiewicz.appsensor.security.user;

import com.google.common.net.InetAddresses;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.owasp.appsensor.core.IPAddress;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * Created by postgres on 2016-09-27.
 */

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends org.owasp.appsensor.core.User{

    public User(String userName, IPAddress address, IPAddress locator) {
        this.locator = locator;
    }

}
