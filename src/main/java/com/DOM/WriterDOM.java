package com.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class WriterDOM {

    Document documentSource;
    StreamResult streamResult;

    public WriterDOM(String source, String result){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            File file = new File("src/main/resources/" + source);
            documentSource = documentBuilder.parse(file);
            documentSource.getDocumentElement().normalize();
            streamResult = new StreamResult("src/main/resources/" + result);
        }
        catch (ParserConfigurationException e){
            System.out.println(e.fillInStackTrace());
        }
        catch (IOException e){
            System.out.println(e.fillInStackTrace());
        }
        catch (SAXException e){
            System.out.println(e.fillInStackTrace());
        }
    }

    public void writeAverage(double average){
        try {
            if (documentSource.getElementsByTagName("average").getLength() > 0) {
                Node averageNode = documentSource.getElementsByTagName("average").item(0);
                averageNode.setTextContent(String.valueOf(average));
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(documentSource), streamResult);
            }
            else {
                Node node = documentSource.getElementsByTagName("student").item(0);
                Element element = documentSource.createElement("average");
                element.setTextContent(String.valueOf(average));
                node.appendChild(element);
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(documentSource), streamResult);
            }
        }
        catch (TransformerException e){
            System.out.println(e.fillInStackTrace());
        }
    }
}
