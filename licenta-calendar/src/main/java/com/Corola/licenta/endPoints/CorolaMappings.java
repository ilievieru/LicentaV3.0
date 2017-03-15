package com.Corola.licenta.endPoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
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

}
