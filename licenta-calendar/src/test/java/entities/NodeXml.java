package entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by P3700664 on 12/7/2016.
 */
public class NodeXml {

    @Test
    public void testNode(){
        com.Corola.licenta.entities.UsersXml usersXml = new com.Corola.licenta.entities.UsersXml("C:\\Users\\p3700664\\IdeaProjects\\LicentaV3.1\\licenta-calendar\\src\\main\\resources\\Users.xml");
        if(usersXml.checkUserAuth("ilie.vieru", "1234")){
            System.out.println("User correct");
        }
    }
}
