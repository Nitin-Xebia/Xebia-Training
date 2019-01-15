package com.customerservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.8
 * 2013-06-20T18:00:53.677+05:30
 * Generated source version: 2.6.8
 * 
 */
@WebServiceClient(name = "CustomerServiceService", 
                  wsdlLocation = "http://localhost:8080/Cxf_WSDL_First/services/CustomerServicePort?wsdl",
                  targetNamespace = "http://customerservice.com/") 
public class CustomerServiceService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://customerservice.com/", "CustomerServiceService");
    public final static QName CustomerServicePort = new QName("http://customerservice.com/", "CustomerServicePort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9090/CustomerServicePort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CustomerServiceService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:9090/CustomerServicePort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CustomerServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CustomerServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CustomerServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns CustomerService
     */
    @WebEndpoint(name = "CustomerServicePort")
    public CustomerService getCustomerServicePort() {
        return super.getPort(CustomerServicePort, CustomerService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CustomerService
     */
    @WebEndpoint(name = "CustomerServicePort")
    public CustomerService getCustomerServicePort(WebServiceFeature... features) {
        return super.getPort(CustomerServicePort, CustomerService.class, features);
    }

}