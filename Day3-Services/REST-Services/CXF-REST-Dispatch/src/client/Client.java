package client;

 

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;

import org.w3c.dom.Document;

import org.apache.cxf.staxutils.StaxUtils;

public final class Client {

    private Client() {
    }

    public static void main(String args[]) throws Exception {
        QName serviceName = new QName("http://apache.org/hello_world_xml_http/wrapped",
                                                "cutomerservice");
        QName portName = new QName("http://apache.org/hello_world_xml_http/wrapped",
                                             "RestProviderPort");
        String endpointAddress = "http://localhost:9000/customerservice/customer";

        // Sent HTTP GET request to query all customer info
        URL url = new URL(endpointAddress);
        System.out.println("Invoking server through HTTP GET to query all customer info");
        InputStream in = url.openStream();
        StreamSource source = new StreamSource(in);
        printSource(source);

        // Sent HTTP GET request to query customer info
        url = new URL(endpointAddress + "?id=1234");
        System.out.println("Invoking server through HTTP GET to query customer info");
        in = url.openStream();
        source = new StreamSource(in);
        printSource(source);

        Service service = Service.create(serviceName);
        service.addPort(portName, HTTPBinding.HTTP_BINDING,  endpointAddress);
        Dispatch<DOMSource> dispatcher = service.createDispatch(portName,
                                                                DOMSource.class, Service.Mode.PAYLOAD);
        Map<String, Object> requestContext = dispatcher.getRequestContext();

        Client client = new Client();
        InputStream is = client.getClass().getResourceAsStream("/Customer1Req.xml");
        Document doc = StaxUtils.read(is);
        DOMSource reqMsg = new DOMSource(doc);

        requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "POST");
        System.out.println("Invoking through HTTP POST to update customer using JAX-WS Dispatch");
        DOMSource result = dispatcher.invoke(reqMsg);
        printSource(result);

        System.out.println("Client Invoking succeeded!");
        System.exit(0);
    }

    private static void printSource(Source source) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            StreamResult sr = new StreamResult(bos);
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            Properties oprops = new Properties();
            oprops.put(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperties(oprops);
            trans.transform(source, sr);
            System.out.println("**** Response ******");
            System.out.println(bos.toString());
            bos.close();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
