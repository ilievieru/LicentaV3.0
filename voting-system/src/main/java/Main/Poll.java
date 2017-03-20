package Main;


import VotingSystems.*;

import java.util.ArrayList;
import java.util.List;

public class Poll {

    private final String COMMAND = "run";
    private VotingSystem system;
    List<String> input;
    String command;
    int choice;

    public Poll(List<String> input, String command, int choice) {
        this.input = input;
        this.choice = choice;
        this.command = command;
    }

    public String runPool(){
        System.out.println(
                "Command type: \n1 for instant runoff voting \n2 for the Borda Count \n3 for" +
                        " the Condorcet Method \nAny other int for plurality voting");

          ArrayList<Ballot> b = parseInput(input, command);
        switch (choice) {
            case 1:
                system = new InstantRunoff(b.toArray(new Ballot[b.size()]));
                break;
            case 2:
                system = new Borda(b.toArray(new Ballot[b.size()]));
                break;
            case 3:
                system = new Condorcet(b.toArray(new Ballot[b.size()]));
                break;
            default:
                system = new Plurality(b.toArray(new Ballot[b.size()]));
        }

        System.out.println("WINNER: " + system.computeWinner());
        System.out.println(system.results());
        return system.results();
    }

    private ArrayList<Ballot> parseInput(List<String> input, String command) {
        ArrayList<Ballot> ballots = new ArrayList<Ballot>();

        String ballot;
        int x = 0;
        while (x < input.size()) {
            if (input.get(x).trim() != null) {
                ballot = input.get(x).trim();

                if (ballot.equalsIgnoreCase(COMMAND)) {
                    return ballots;
                }
                int n = 1;
                int index = ballot.indexOf(' ');
                if (index != -1 && index < ballot.length() - 1) {
                    try {
                        n = Integer.parseInt(ballot.substring(0, index));
                        ballot = ballot.substring(index);
                    } catch (NumberFormatException e) {
                        n = 1;
                    }
                }
                for (int i = 0; i < n; i++) {
                    if (ballot.length() > 0)
                        ballots.add(new Ballot(ballot));
                }
                x++;
            }
        }
        return ballots;
    }
}
