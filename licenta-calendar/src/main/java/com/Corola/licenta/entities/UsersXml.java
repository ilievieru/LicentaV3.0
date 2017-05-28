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
public class UsersXml {

    List<User> usersList;

    public UsersXml(String path) {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setIgnoringComments(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            NodeList users = doc.getElementsByTagName("user");
            int usersNumber = users.getLength();
            usersList = new ArrayList<>();

            for (int i = 0; i < usersNumber; i++) {

                NodeList user = users.item(i).getChildNodes();
                String userId = user.item(1).getTextContent();
                String userName = user.item(3).getTextContent();
                String firstName = user.item(5).getTextContent();
                String lastName = user.item(7).getTextContent();
                String password = user.item(9).getTextContent();
                String securityLevel = user.item(11).getTextContent();

                NodeList events = user.item(13).getChildNodes();
                List<String> eventsList = new ArrayList<>();
                int eventPos = 1;
                for (int j = 1; j < events.getLength(); j++) {
                    if (eventPos <= j) {
                        eventsList.add(events.item(eventPos).getTextContent());
                        eventPos = eventPos + 2;
                    }
                }

                User userItem = new User(userId, userName, firstName, lastName, password, securityLevel, eventsList);
                usersList.add(userItem);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Boolean checkUserAuth(String userName, String userPassword) {
        for (int user = 0; user < usersList.size(); user++) {
            if (userName.equals(usersList.get(user).getUserName()) && userPassword.equals(usersList.get(user).getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User findUser(String id) {
        User notFound = new User();
        notFound.setFirstName("User not found");
        for (int user = 0; user < usersList.size(); user++) {
            if (id.equals(usersList.get(user).getUserId())) {
                return usersList.get(user);
            }
        }
        return notFound;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<User> getUsersList() {

        return usersList;
    }
}
