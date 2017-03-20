import Main.Poll;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by P3700664 on 20.03.2017.
 */
public class TestVotingSystem {
    @Test
    public void testInstantRunOff() {
        List<String> input = new ArrayList<String>();
        input.add("Ion");
        input.add("vasile");
        input.add("ilie");
        input.add("ilie");

        String command = "run";
        Poll p = new Poll(input, command, 1);
    }

    @Test
    public void testBorda() {
        List<String> input = new ArrayList<String>();
        input.add("Ion");
        input.add("vasile");
        input.add("ilie");
        input.add("ilie");

        String command = "run";
        Poll p = new Poll(input, command, 2);
    }
    @Test
    public void testCondorcet() {
        List<String> input = new ArrayList<String>();
        input.add("Ion");
        input.add("vasile");
        input.add("ilie");
        input.add("ilie");

        String command = "run";
        Poll p = new Poll(input, command, 3);
    }
    @Test
    public void testPlurality() {
        List<String> input = new ArrayList<String>();
        input.add("Ion");
        input.add("vasile");
        input.add("ilie");
        input.add("ilie");

        String command = "run";
        Poll p = new Poll(input, command, 4);
    }
}
