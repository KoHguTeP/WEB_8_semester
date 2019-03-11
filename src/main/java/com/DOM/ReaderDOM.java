package com.DOM;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ReaderDOM {

    Document document;

    public ReaderDOM(String filename) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            File file = new File("src/main/resources/" + filename);
            document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
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

    public double checkAverage() {
        int sum = 0;
        double count = 0;
        double res;
        if (document.getElementsByTagName("subject").getLength() > 0) {
            for (int i = 0; i < document.getElementsByTagName("subject").getLength(); i++) {
                sum += Integer.parseInt(((Element) document.getElementsByTagName("subject").item(i)).getAttribute("mark"));
                count++;
            }
            if (document.getDocumentElement().getElementsByTagName("average").getLength() > 0) {
                if (document.getDocumentElement().getElementsByTagName("average").item(0).getTextContent().equals(sum / count))
                    res = (double)Math.round((sum / count) * 100d) / 100d;//return -1;//совпало
                else res = (double)Math.round((sum / count) * 100d) / 100d;//return Math.round(sum / count * 100) / 100.0;//не совпало
            } else res = (double)Math.round((sum / count) * 100d) / 100d;//return Math.round(sum / count * 100) / 100.0;//нет средней
            return res;
        }
        return -2;//нет предметов
    }
/*
    public void printNodes(){
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++)
            if (nodeList.item(i).getNodeType() == nodeList.item(i).ELEMENT_NODE)
                System.out.println(nodeList.item(i).getNodeName() + "   ||    " + nodeList.item(i).getNodeType());
        //System.out.println(document.getDocumentElement().getTagName());
    }*/
/*
    public boolean checkStuct(){
        int k = 0;
        if (document.getDocumentElement().getTagName().equals("student")) {
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if ((nodeList.item(i).getNodeType() == nodeList.item(i).ELEMENT_NODE) &&
                        (nodeList.item(i).getNodeName().equals("subject")) && (nodeList.item(i).getNodeName().equals("average"))) {
                    if (nodeList.item(i).getNodeName().equals("subject"))
                        k++;
                    else if (nodeList.item(i).getNodeName().equals("average"))
                        break;
                }
            }

            return (document.getElementsByTagName("subject").getLength() == k);
        }
        else return false;//мейн тэг не студент
    }*/
}
