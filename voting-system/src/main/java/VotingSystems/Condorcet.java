package VotingSystems;

import java.util.HashMap;
import java.util.Map;

public class Condorcet extends VotingSystem {

    private Map<String, Map<String, Integer>> candVotes;

    public Condorcet() {
    }

    public Condorcet(Ballot[] ballots) {
        super(ballots);
    }

    @Override
    protected void initCandidates(Ballot[] ballots) {
        candVotes = new HashMap<String, Map<String, Integer>>();

        for (Ballot ballot : ballots) {
            for (String cand : ballot.toArray()) {
                if (!candVotes.containsKey(cand))
                    candVotes.put(cand, new HashMap<String, Integer>());
            }
        }

        for (Map.Entry<String, Map<String, Integer>> outer : candVotes.entrySet()) {
            for (Map.Entry<String, Map<String, Integer>> inner : candVotes.entrySet()) {
                if (!outer.getKey().equals(inner.getKey())) {
                    outer.getValue().put(inner.getKey(), 0);
                }
            }
        }
    }

    @Override
    protected void setVotes() {
        //for each ballot
        for (Ballot ballot : voterBallots) {
            //and for each candidate
            for (Map.Entry<String, Map<String, Integer>> outer : candVotes.entrySet()) {
                //against each candidate
                for (Map.Entry<String, Integer> inner : outer.getValue().entrySet()) {
                    int outerI = ballot.getIndex(outer.getKey()); //Outer index
                    int innerI = ballot.getIndex(inner.getKey());

                    if (innerI == -1) { //If the inner candidate is not ranked
                        if (outerI != -1) {//but the outer one is,
                            //increment the votes of outer against inner.
                            inner.setValue(inner.getValue() + 1);
                        }
                    } else if (outerI < innerI) {//Lower index --> higher preference
                        inner.setValue(inner.getValue() + 1);
                    }
                }
            }
        }
    }

    protected String getWinner() {
        String winner = "";

        Map<String, Boolean> isDefeated = new HashMap<String, Boolean>();

        //Compares each candidate against each other candidate.
        for (Map.Entry<String, Map<String, Integer>> outer : candVotes.entrySet()) {
            //against each candidate

            isDefeated.put(outer.getKey(), false);

            for (Map.Entry<String, Integer> inner : outer.getValue().entrySet()) {
                if (inner.getValue() < voterBallots.size() / 2 + 1) {
                    isDefeated.put(outer.getKey(), true);
                }
            }
        }

        //Checks for an undefeated candidate.
        for (Map.Entry<String, Boolean> entry : isDefeated.entrySet()) {
            if (entry.getValue() == false)
                winner = entry.getKey();
        }

        return winner;
    }

    @Override
    public String results() {
        String table = String.format("%-18s", " ");

        //Top row
        for (Map.Entry<String, Map<String, Integer>> entry : candVotes.entrySet())
            table += String.format("%-18s", entry.getKey());


        for (Map.Entry<String, Map<String, Integer>> outer : candVotes.entrySet()) {
            table += String.format("\n%-18s", outer.getKey());

            for (Map.Entry<String, Map<String, Integer>> inner : candVotes.entrySet()) {
                if (outer.getKey().equals(inner.getKey())) {
                    table += String.format("%-18s", "-");
                } else {
                    table += String.format("%-18d", outer.getValue().get(inner.getKey()));
                }
            }
        }

        return table;
    }

    @Override
    protected boolean isTied(String candidate) {
        return false;
    }
}