package entities;

import com.Corola.licenta.entities.Event;
import com.Corola.licenta.entities.EventsXml;
import com.Corola.licenta.entities.User;
import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by P3700664 on 12/7/2016.
 */
public class NodeXml {

    @Test
    public void testUserXml() {
        com.Corola.licenta.entities.UsersXml usersXml = new com.Corola.licenta.entities.UsersXml("C:\\Users\\p3700664\\IdeaProjects\\LicentaV3.1\\licenta-calendar\\src\\main\\resources\\Users.xml");
        if (usersXml.checkUserAuth("ilie.vieru", "1234")) {
            System.out.println("User correct");
        }
    }

    @Test
    public void testEventXml() {
        com.Corola.licenta.entities.EventsXml eventsXml = new com.Corola.licenta.entities.EventsXml("C:\\Users\\p3700664\\IdeaProjects\\LicentaV3.1\\licenta-calendar\\src\\main\\resources\\Events.xml");
    }

    @Test
    public void testInsetEvent() {
        String path = "C:\\Users\\p3700664\\IdeaProjects\\LicentaV3.1\\licenta-calendar\\src\\main\\resources\\EventsTest.xml";
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("company");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("Staff");
            rootElement.appendChild(staff);

            // set attribute to staff element
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            staff.setAttributeNode(attr);

            // shorten way
            // staff.setAttribute("id", "1");

            // firstname elements
            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode("yong"));
            staff.appendChild(firstname);

            // lastname elements
            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("mook kim"));
            staff.appendChild(lastname);

            // nickname elements
            Element nickname = doc.createElement("nickname");
            nickname.appendChild(doc.createTextNode("mkyong"));
            staff.appendChild(nickname);

            // salary elements
            Element salary = doc.createElement("salary");
            salary.appendChild(doc.createTextNode("100000"));
            staff.appendChild(salary);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    @Test
    public void testInsetEvent1() {
        String path = "C:\\Users\\p3700664\\IdeaProjects\\LicentaV3.1\\licenta-calendar\\src\\main\\resources\\EventsTest.xml";
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path);

            Node events = doc.getFirstChild();
            Element event = doc.createElement("event");

            Element eventId = doc.createElement("eventId");
            eventId.setTextContent("3");
            event.appendChild(eventId);


            Element name = doc.createElement("name");
            name.setTextContent("Second event created from java");
            event.appendChild(name);

            Element description = doc.createElement("description");
            description.setTextContent("First event created from java. This is the description");
            event.appendChild(description);

            Element status = doc.createElement("status");
            status.setTextContent("1");
            event.appendChild(status);

            Element users = doc.createElement("users");

            Element userId = doc.createElement("usersId");
            userId.setTextContent("1");
            users.appendChild(userId);
            Element userId1 = doc.createElement("usersId");
            userId1.setTextContent("2");
            users.appendChild(userId1);

            event.appendChild(users);

            Element dates = doc.createElement("dates");

            Element date = doc.createElement("date");
            date.setTextContent("01.01.2017");
            dates.appendChild(date);
            Element date1 = doc.createElement("date");
            date1.setTextContent("02.01.2017");
            dates.appendChild(date1);
            Element date2 = doc.createElement("date");
            date2.setTextContent("03.01.2017");
            dates.appendChild(date2);
            Element date3 = doc.createElement("date");
            date3.setTextContent("04.01.2017");
            dates.appendChild(date3);

            event.appendChild(dates);

            events.appendChild(event);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            /*Write content*/
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);

            System.out.println("Done");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertEventParam(){
        User user = new User();
        user.setUserId("1");
        User user1 = new User();
        user1.setUserId("2");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        String date = "01.01.2017";
        String date1 = "02.01.2017";
        String date2 = "03.01.2017";
        String date3 = "04.01.2017";
        List<String> dates = new ArrayList<>();
        dates.add(date);
        dates.add(date1);
        dates.add(date2);
        dates.add(date3);

        Event event = new Event();
        event.setEventId("10");
        event.setEventName("Event name 10");
        event.setEventDescriotion("This is another test event");
        event.setStatus("1");
        event.setUserList(userList);
        event.setDates(dates);

        EventsXml eventsXml = new EventsXml();
        eventsXml.insertElement(event);
    }
}
