package com.Corola.licenta.endPoints;

import Main.Poll;
import com.Corola.licenta.entities.User;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Spirit on 11/13/2016.
 */
@RestController
public class CorolaMappings {
    Logger logger = LoggerFactory.getLogger(CorolaMappings.class);

    @RequestMapping(value = "/secondResources", method = RequestMethod.GET)
    public Map<String, String> getSecondResources() {

        Map<String, String> data = new HashMap<String, String>();
        data.put("first", "secondValues");
        return data;
    }

    @RequestMapping(value = "/firstResources", method = RequestMethod.GET)
    public Map<String, String> getFirstResource() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("first", "firstValues");
        return data;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String multipleSave(@RequestParam("file") MultipartFile[] files) {
        String fileName = null;
        String msg = "";
        BufferedOutputStream buffStream = null;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    File file = new File("Folder\\" + fileName);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    buffStream = new BufferedOutputStream(fileOutputStream);
                    buffStream.write(bytes);
                    msg += "You have successfully uploaded " + fileName + "<br/>";
                } catch (Exception e) {
                    return "You failed to upload " + fileName + ": " + e.getMessage() + "<br/>";
                } finally {
                    try {
                        buffStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }

    @Timed
    @RequestMapping(value = "/BordaVotingEndpoint", method = RequestMethod.GET)
    public Map<String, String> getBorda(@RequestParam(value = "input") List<String> input) {
        Map<String, String> data = new HashMap<String, String>();
        String command = "run";
        Poll p = new Poll(input, command, 2);
        data.put("Winner", p.runPool());
        return data;
    }

    @Timed
    @RequestMapping(value = "/InstantRunOffVotingEndpoint", method = RequestMethod.GET)
    public Map<String, String> getInstantRunOff(@RequestParam(value = "input") List<String> input) {
        Map<String, String> data = new HashMap<String, String>();
        String command = "run";
        Poll p = new Poll(input, command, 1);
        data.put("Winner", p.runPool());
        return data;
    }

    @Timed
    @RequestMapping(value = "/CondorcetVotingEndpoint", method = RequestMethod.GET)
    public Map<String, String> getCondorcet(@RequestParam(value = "input") List<String> input) {
        Map<String, String> data = new HashMap<String, String>();
        String command = "run";
        Poll p = new Poll(input, command, 3);
        data.put("Winner", p.runPool());
        return data;
    }

    @Timed
    @RequestMapping(value = "/PluralityVotingEndpoint", method = RequestMethod.GET)
    public Map<String, String> getPlurality(@RequestParam(value = "input") List<String> input) {
        Map<String, String> data = new HashMap<String, String>();
        String command = "run";
        Poll p = new Poll(input, command, 4);
        data.put("Winner", p.runPool());
        return data;
    }

    @Timed
    @RequestMapping(value = "/Auth", method = RequestMethod.GET)
    public Map<String, Boolean> auth(@RequestParam(value = "userName") String username, @RequestParam(value = "password") String password) {
        Map<String, Boolean> data = new HashMap<String, Boolean>();
        com.Corola.licenta.entities.UsersXml usersXml = new com.Corola.licenta.entities.UsersXml("C:\\Users\\p3700664\\IdeaProjects\\LicentaV3.1\\licenta-calendar\\src\\main\\resources\\Users.xml");
        if (usersXml.checkUserAuth(username, password))
            data.put("Success", true);
        else
            data.put("Succes", false);

        return data;
    }

    @Timed
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public List<Map<String, String>> getUsers() {
        com.Corola.licenta.entities.UsersXml usersXml = new com.Corola.licenta.entities.UsersXml("C:\\Users\\p3700664\\IdeaProjects\\LicentaV3.1\\licenta-calendar\\src\\main\\resources\\Users.xml");

        List<Map<String,String>> result = new ArrayList<>();
        List<User> users = usersXml.getUsersList();
        for (int i = 0; i < users.size(); i++) {
            Map<String, String> data = new HashMap<>();
            data.put("Id",users.get(i).getUserId());
            data.put("Name",users.get(i).getFirstName() + " " + users.get(i).getLastName());
            result.add(data);
        }
        return result;
    }
}
