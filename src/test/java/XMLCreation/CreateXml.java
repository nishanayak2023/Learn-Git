package XMLCreation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXml {
    public static void main(String[] args) {
        try {
            // Create document builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Create document
            Document document = builder.newDocument();

            // Create root element
            Element dataPDU = document.createElementNS("urn:swift:saa:xsd:saa.2.0", "Saa:DataPDU");
            document.appendChild(dataPDU);

            // Create child elements
            Element revision = document.createElement("Saa:Revision");
            revision.setTextContent("2.0.8");
            dataPDU.appendChild(revision);

            // Create other elements and add them similarly...

            // Write the content to an XML file
            // You can use Transformer to serialize the document to XML file

            // Print the XML content
            System.out.println(convertDocumentToString(document));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert Document to String
    private static String convertDocumentToString(Document document) {
        try {
            javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
            java.io.StringWriter stringWriter = new java.io.StringWriter();
            javax.xml.transform.stream.StreamResult streamResult = new javax.xml.transform.stream.StreamResult(stringWriter);
            transformer.transform(source, streamResult);
            return stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
