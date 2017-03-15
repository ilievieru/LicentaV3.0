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
    private String name;
    private String version;
    private String use;
    private String level;


    public NodeXml(String path) {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setIgnoringComments(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            NodeList element = doc.getElementsByTagName("name");
            name = element.item(0).getTextContent();
            NodeList element1 = doc.getElementsByTagName("version");
            version = element1.item(0).getTextContent();
            NodeList element2 = doc.getElementsByTagName("use");
            use = element2.item(0).getTextContent();
            NodeList element3 = doc.getElementsByTagName("level");
            level = element3.item(0).getTextContent();


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
