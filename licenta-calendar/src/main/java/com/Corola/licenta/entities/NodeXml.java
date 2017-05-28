package com.Corola.licenta.entities;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by P3700664 on 12/7/2016.
 */
public class NodeXml {
    private String firstName;
    private String lastName;
    private String password;
    private String securityLevel;


    public NodeXml(String path) {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setIgnoringComments(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            NodeList element = doc.getElementsByTagName("firstName");
            firstName = element.item(0).getTextContent();
            NodeList element1 = doc.getElementsByTagName("lastName");
            lastName = element1.item(0).getTextContent();
            NodeList element2 = doc.getElementsByTagName("password");
            password = element2.item(0).getTextContent();
            NodeList element3 = doc.getElementsByTagName("securityLevel");
            securityLevel = element3.item(0).getTextContent();


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
            this.securityLevel = securityLevel;
    }

}
