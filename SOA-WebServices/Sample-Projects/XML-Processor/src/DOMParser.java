 

// import JAXP packages
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.w3c.dom.*;

import java.io.*;


 

public class DOMParser
 {
    /** All output will use this encoding */
    static final String outputEncoding = "UTF-8";

    /** Output goes here */
    private PrintWriter out;

    /** Indent level */
    private int indent = 0;

    /** Indentation will be in multiples of basicIndent  */
    private final String basicIndent = "  ";

    /** Constants used for JAXP 1.2 */
    static final String JAXP_SCHEMA_LANGUAGE =
        "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA =
        "http://www.w3.org/2001/XMLSchema";
    static final String JAXP_SCHEMA_SOURCE =
        "http://java.sun.com/xml/jaxp/properties/schemaSource";

    DOMParser(PrintWriter out)
	{
        this.out = out;
    }

    /**
     * Echo common attributes of a DOM2 Node and terminate output with an
     * EOL character.
     */
    private void printlnCommon(Node n) 
	{
        out.print(" nodeName=\"" + n.getNodeName() + "\"");

        String val = n.getNamespaceURI();
        if (val != null) {
            out.print(" uri=\"" + val + "\"");
        }

        val = n.getPrefix();
        if (val != null) {
            out.print(" pre=\"" + val + "\"");
        }

        val = n.getLocalName();
        if (val != null) {
            out.print(" local=\"" + val + "\"");
        }

        val = n.getNodeValue();
        if (val != null) {
            out.print(" nodeValue=");
            if (val.trim().equals("")) {
                // Whitespace
                out.print("[WS]");
            } else {
                out.print("\"" + n.getNodeValue() + "\"");
            }
        }
        out.println();
    }

    /**
     * Indent to the current level in multiples of basicIndent
     */
    private void outputIndentation()
	{
        for (int i = 0; i < indent; i++) {
            out.print(basicIndent);
        }
    }

    /**
     * Recursive routine to print out DOM tree nodes
     */
    private void echo(Node n)
	{
        // Indent to the current level before printing anything
        outputIndentation();

        int type = n.getNodeType();
        switch (type) 
		{
        case Node.ATTRIBUTE_NODE:
            out.print("ATTR:");
            printlnCommon(n);
            break;
        case Node.CDATA_SECTION_NODE:
            out.print("CDATA:");
            printlnCommon(n);
            break;
        case Node.COMMENT_NODE:
            out.print("COMM:");
            printlnCommon(n);
            break;
        case Node.DOCUMENT_FRAGMENT_NODE:
            out.print("DOC_FRAG:");
            printlnCommon(n);
            break;
        case Node.DOCUMENT_NODE:
            out.print("DOC:");
            printlnCommon(n);
            break;
        case Node.DOCUMENT_TYPE_NODE:
            out.print("DOC_TYPE:");
            printlnCommon(n);

            // Print entities if any
            NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
            indent += 2;
            for (int i = 0; i < nodeMap.getLength(); i++) {
                Entity entity = (Entity)nodeMap.item(i);
                echo(entity);
            }
            indent -= 2;
            break;
        case Node.ELEMENT_NODE:
            out.print("ELEM:");
            printlnCommon(n);

            // Print attributes if any.  Note: element attributes are not
            // children of ELEMENT_NODEs but are properties of their
            // associated ELEMENT_NODE.   

            NamedNodeMap atts = n.getAttributes();
            indent += 2;
            for (int i = 0; i < atts.getLength(); i++) {
                Node att = atts.item(i);
                echo(att);
            }
            indent -= 2;
            break;
        case Node.ENTITY_NODE:
            out.print("ENT:");
            printlnCommon(n);
            break;
        case Node.ENTITY_REFERENCE_NODE:
            out.print("ENT_REF:");
            printlnCommon(n);
            break;
        case Node.NOTATION_NODE:
            out.print("NOTATION:");
            printlnCommon(n);
            break;
        case Node.PROCESSING_INSTRUCTION_NODE:
            out.print("PROC_INST:");
            printlnCommon(n);
            break;
        case Node.TEXT_NODE:
            out.print("TEXT:");
            printlnCommon(n);
            break;
        default:
            out.print("UNSUPPORTED NODE: " + type);
            printlnCommon(n);
            break;
        }

        // Print children if any
        indent++;
        for (Node child = n.getFirstChild(); child != null;
             child = child.getNextSibling()) {
            echo(child);
        }
        indent--;
    }

     

    public static void main(String[] args) throws Exception
	{
        String filename = null;
        boolean dtdValidate = false;
        boolean xsdValidate = false;
		boolean  Validate = false;
        String schemaSource = null;

        boolean ignoreWhitespace = false;
        boolean ignoreComments = false;
        boolean putCDATAIntoText = false;
        boolean createEntityRefs = false;

       

        if(args.length > 0)
             filename = args[0];

		if(args.length > 1)
		{
            schemaSource = args[1];
			Validate = true;
			if(schemaSource.endsWith(".xsd"))
                xsdValidate = true;
		}
        if (filename == null)
		{
            System.out.println("Pl. enter the proper file names..");
			System.exit(0);
        }

        // Step 1: create a DocumentBuilderFactory and configure it
        DocumentBuilderFactory dbf =
            DocumentBuilderFactory.newInstance();

        // Set namespaceAware to true to get a DOM Level 2 tree with nodes
        // containing namesapce information.   

        dbf.setNamespaceAware(true);

        // Set the validation mode to either: no validation, DTD
        // validation, or XSD validation

        dbf.setValidating(Validate);		

        if (xsdValidate) 
			{
            try
				{
                dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
               }
			catch (IllegalArgumentException x)
				{
                // This can happen if the parser does not support JAXP 1.2
                System.err.println(
                    "Error: JAXP DocumentBuilderFactory attribute not recognized: "
                    + JAXP_SCHEMA_LANGUAGE);
                System.err.println(
                    "Check to see if parser conforms to JAXP 1.2 spec.");
                System.exit(1);
            }
        }

        // Set the schema source, if any.   

     try
	  {
        if ((schemaSource != null)&(xsdValidate == true) )
			{
            dbf.setAttribute(JAXP_SCHEMA_SOURCE, new File(schemaSource));
            }

        // Optional: set various configuration options
        dbf.setIgnoringComments(ignoreComments);
        dbf.setIgnoringElementContentWhitespace(ignoreWhitespace);
        dbf.setCoalescing(putCDATAIntoText);
        // The opposite of creating entity ref nodes is expanding them inline

        dbf.setExpandEntityReferences(!createEntityRefs);

        // Step 2: create a DocumentBuilder that satisfies the constraints
        // specified by the DocumentBuilderFactory

        DocumentBuilder db = dbf.newDocumentBuilder();

        // Set an ErrorHandler before parsing
        OutputStreamWriter errorWriter =
            new OutputStreamWriter(System.err, outputEncoding);

        db.setErrorHandler(new MyErrorHandler(new PrintWriter(errorWriter, true)));

        // Step 3: parse the input file create the document object 
        Document doc = db.parse(new File(filename));

        // Print out the DOM tree
        
        //OutputStreamWriter outWriter =  new OutputStreamWriter(new FileOutputStream("output"), outputEncoding);
     
        OutputStreamWriter outWriter =  new OutputStreamWriter(System.out, outputEncoding);
        DOMParser xmlParser =  new DOMParser(new PrintWriter(outWriter, true));
		xmlParser.echo(doc);
		System.out.println("Validation Successfull!");
	  }
	  catch(Exception e)
		{
           System.out.println("Error in Parsing XML is  "+e);
		}
    }

    // Error handler to report errors and warnings
    private static class MyErrorHandler implements ErrorHandler 
	 {
        /** Error handler output goes here */
        private PrintWriter out;

        MyErrorHandler(PrintWriter out)
		{
            this.out = out;
        }

        /**
         * Returns a string describing parse exception details
         */
        private String getParseExceptionInfo(SAXParseException spe)
		{
            String systemId = spe.getSystemId();
            if (systemId == null) {
                systemId = "null";
            }
            String info = "URI=" + systemId +
                " Line=" + spe.getLineNumber() +
                ": " + spe.getMessage();
            return info;
        }

        // The following methods are standard SAX ErrorHandler methods.
        // See SAX documentation for more info.

        public void warning(SAXParseException spe) throws SAXException 
		{
            out.println("Warning: " + getParseExceptionInfo(spe));
        }
        
        public void error(SAXParseException spe) throws SAXException
		{
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }

        public void fatalError(SAXParseException spe) throws SAXException 
		{
            String message = "Fatal Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    }
}
