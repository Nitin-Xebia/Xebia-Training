
package com.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.6.8
 * Sat Jun 22 10:25:29 IST 2013
 * Generated source version: 2.6.8
 */

@XmlRootElement(name = "submitPOResponse", namespace = "http://com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitPOResponse", namespace = "http://com/")

public class SubmitPOResponse {

    @XmlElement(name = "return")
    private com.ShipmentNotice _return;

    public com.ShipmentNotice getReturn() {
        return this._return;
    }

    public void setReturn(com.ShipmentNotice new_return)  {
        this._return = new_return;
    }

}
