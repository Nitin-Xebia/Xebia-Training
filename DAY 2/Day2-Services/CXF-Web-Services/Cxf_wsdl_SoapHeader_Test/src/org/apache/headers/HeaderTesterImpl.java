
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package org.apache.headers;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.8
 * 2013-06-30T22:12:18.794+05:30
 * Generated source version: 2.6.8
 * 
 */

@javax.jws.WebService(
                      serviceName = "HeaderService",
                      portName = "SoapPort",
                      targetNamespace = "http://apache.org/headers",
                      wsdlLocation = "http://localhost:9000/headers?wsdl",
                      endpointInterface = "org.apache.headers.HeaderTester")
                      
public class HeaderTesterImpl implements HeaderTester {

    private static final Logger LOG = Logger.getLogger(HeaderTesterImpl.class.getName());

    /* (non-Javadoc)
     * @see org.apache.headers.HeaderTester#outHeader(org.apache.headers.OutHeader  me ,)org.apache.headers.OutHeaderResponse  theResponse ,)org.apache.headers.SOAPHeaderData  headerInfo )*
     */
    public void outHeader(OutHeader me,javax.xml.ws.Holder<OutHeaderResponse> theResponse,javax.xml.ws.Holder<SOAPHeaderData> headerInfo) { 
        LOG.info("Executing operation outHeader");
        System.out.println(me);
        try {
            org.apache.headers.OutHeaderResponse theResponseValue = new org.apache.headers.OutHeaderResponse();
            theResponseValue.setResponseType("ResponseType2005054261");
            theResponse.value = theResponseValue;
            org.apache.headers.SOAPHeaderData headerInfoValue = new org.apache.headers.SOAPHeaderData();
            headerInfoValue.setOriginator("Originator-101451786");
            headerInfoValue.setMessage("Message-1800958036");
            headerInfo.value = headerInfoValue;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.headers.HeaderTester#inHeader(org.apache.headers.InHeader  me ,)org.apache.headers.SOAPHeaderData  headerInfo )*
     */
    public org.apache.headers.InHeaderResponse inHeader(InHeader me,SOAPHeaderData headerInfo) { 
        LOG.info("Executing operation inHeader");
        System.out.println(me);
        System.out.println(headerInfo);
        try {
            org.apache.headers.InHeaderResponse _return = new org.apache.headers.InHeaderResponse();
            _return.setResponseType("ResponseType-1814616185");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.headers.HeaderTester#inoutHeader(org.apache.headers.InoutHeader  me ,)org.apache.headers.SOAPHeaderData  headerInfo )*
     */
    public org.apache.headers.InoutHeaderResponse inoutHeader(InoutHeader me,javax.xml.ws.Holder<SOAPHeaderData> headerInfo) { 
        LOG.info("Executing operation inoutHeader");
        System.out.println(me);
        System.out.println(headerInfo.value);
        try {
            org.apache.headers.InoutHeaderResponse _return = new org.apache.headers.InoutHeaderResponse();
            _return.setResponseType("ResponseType1589963637");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}