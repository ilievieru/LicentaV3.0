package entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by P3700664 on 12/7/2016.
 */
public class NodeXml {

    @Test
    public void testNode(){
        com.Corola.licenta.entities.NodeXml nodeXml = new com.Corola.licenta.entities.NodeXml("C:\\Users\\p3700664\\IdeaProjects\\LicentaV2.0\\processes\\discourse-parser\\src\\main\\resources\\metadata.xml");
        System.out.println(nodeXml.getName());
        assertEquals(nodeXml.getName(),"DiscourseParser");
    }
}
