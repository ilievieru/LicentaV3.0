package com.Corola.licenta.entities;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by P3700664 on 5/28/2017.
 */
public class EventsXml {
    List<Event> eventList;
    UsersXml usersXml =  new UsersXml("C:\\Users\\p3700664\\IdeaProjects\\LicentaV3.1\\licenta-calendar\\src\\main\\resources\\Users.xml");

    public EventsXml(String path) {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setIgnoringComments(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            NodeList events = doc.getElementsByTagName("event");
            int eventsNumber = events.getLength();
            eventList = new ArrayList<>();

            for (int i = 0; i < eventsNumber; i++) {

                NodeList event = events.item(i).getChildNodes();
                String eventId = event.item(1).getTextContent();
                String eventName = event.item(3).getTextContent();
                String eventDescription = event.item(5).getTextContent();
                String status = event.item(7).getTextContent();

                NodeList eventUsers = event.item(9).getChildNodes();
                List<User> eventsUserList = new ArrayList<>();
                int userPos = 1;
                for (int j = 1; j < eventUsers.getLength(); j++) {
                    if (userPos <= j) {
                        eventsUserList.add(usersXml.findUser(eventUsers.item(userPos).getTextContent()));
                        userPos = userPos + 2;
                    }
                }

                NodeList dates = event.item(11).getChildNodes();
                List<String> datesList = new ArrayList<>();
                int datePos = 1;
                for (int j = 1; j < dates.getLength(); j++) {
                    if (datePos <= j) {
                        datesList.add(dates.item(datePos).getTextContent());
                        datePos = datePos + 2;
                    }
                }
                Event currentEvent = new Event(eventId,eventName,eventDescription,status, eventsUserList,datesList);
                eventList.add(currentEvent);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getEventList() {
        return eventList;
    }
}
