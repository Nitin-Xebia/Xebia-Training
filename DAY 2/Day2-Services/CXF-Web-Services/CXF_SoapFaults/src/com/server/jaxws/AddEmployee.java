
package com.server.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.6.8
 * Fri Jun 21 01:01:01 IST 2013
 * Generated source version: 2.6.8
 */

@XmlRootElement(name = "addEmployee", namespace = "http://server.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addEmployee", namespace = "http://server.com/")

public class AddEmployee {

    @XmlElement(name = "arg0")
    private com.server.Employee arg0;

    public com.server.Employee getArg0() {
        return this.arg0;
    }

    public void setArg0(com.server.Employee newArg0)  {
        this.arg0 = newArg0;
    }

}

