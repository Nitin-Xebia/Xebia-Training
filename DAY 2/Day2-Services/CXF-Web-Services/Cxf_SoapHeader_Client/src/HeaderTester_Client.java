


 
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.headers.HeaderService;
import org.apache.headers.HeaderTester;

 
public final class HeaderTester_Client {

    private static final QName SERVICE_NAME = new QName("http://apache.org/headers", "HeaderService");

    private HeaderTester_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = HeaderService.WSDL_LOCATION;
         
      
        HeaderService ss = new HeaderService(wsdlURL, SERVICE_NAME);
        HeaderTester port = ss.getSoapPort();  
        
        {
        System.out.println("Invoking outHeader...");
        org.apache.headers.OutHeader _outHeader_me = new org.apache.headers.OutHeader();
        _outHeader_me.setRequestType("RequestType1061653651");
        javax.xml.ws.Holder<org.apache.headers.OutHeaderResponse> _outHeader_theResponse = new javax.xml.ws.Holder<org.apache.headers.OutHeaderResponse>();
        javax.xml.ws.Holder<org.apache.headers.SOAPHeaderData> _outHeader_headerInfo = new javax.xml.ws.Holder<org.apache.headers.SOAPHeaderData>();
        port.outHeader(_outHeader_me, _outHeader_theResponse, _outHeader_headerInfo);

        System.out.println("outHeader._outHeader_theResponse=" + _outHeader_theResponse.value);
        System.out.println("outHeader._outHeader_headerInfo=" + _outHeader_headerInfo.value);

        }
        {
        System.out.println("Invoking inHeader...");
        org.apache.headers.InHeader _inHeader_me = new org.apache.headers.InHeader();
        _inHeader_me.setRequestType("RequestType1969723016");
        org.apache.headers.SOAPHeaderData _inHeader_headerInfo = new org.apache.headers.SOAPHeaderData();
        _inHeader_headerInfo.setOriginator("Originator-1662693469");
        _inHeader_headerInfo.setMessage("Message547474050");
        org.apache.headers.InHeaderResponse _inHeader__return = port.inHeader(_inHeader_me, _inHeader_headerInfo);
        System.out.println("inHeader.result=" + _inHeader__return);


        }
        {
        System.out.println("Invoking inoutHeader...");
        org.apache.headers.InoutHeader _inoutHeader_me = new org.apache.headers.InoutHeader();
        _inoutHeader_me.setRequestType("RequestType-804421651");
        org.apache.headers.SOAPHeaderData _inoutHeader_headerInfoVal = new org.apache.headers.SOAPHeaderData();
        _inoutHeader_headerInfoVal.setOriginator("Originator-421290532");
        _inoutHeader_headerInfoVal.setMessage("Message619695341");
        javax.xml.ws.Holder<org.apache.headers.SOAPHeaderData> _inoutHeader_headerInfo = new javax.xml.ws.Holder<org.apache.headers.SOAPHeaderData>(_inoutHeader_headerInfoVal);
        org.apache.headers.InoutHeaderResponse _inoutHeader__return = port.inoutHeader(_inoutHeader_me, _inoutHeader_headerInfo);
        System.out.println("inoutHeader.result=" + _inoutHeader__return);

        System.out.println("inoutHeader._inoutHeader_headerInfo=" + _inoutHeader_headerInfo.value);

        }

        System.exit(0);
    }

}
