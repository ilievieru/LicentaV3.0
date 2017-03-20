package VotingSystems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class VotingSystem {

    protected List<Ballot> voterBallots;

    protected Map<String, Integer> candVotes;

    public VotingSystem() {
    }

    public VotingSystem(Ballot[] ballots) {
        initBallots(ballots);
        initCandidates(ballots);
        setVotes();
    }

    protected void initBallots(Ballot[] ballots) {
        voterBallots = new ArrayList<Ballot>(); //Sets the ballots list.

        for (int i = 0; i < ballots.length; i++) {    //For each ballot,
            if (ballots[i] != null) {                    //if it is not null,
                //add it to the ballots array.
                voterBallots.add(new Ballot(ballots[i].toArray()));
            }
        }
    }

    protected void initCandidates(Ballot[] ballots) {
        candVotes = new HashMap<String, Integer>();

        for (Ballot ballot : ballots) {
            for (String cand : ballot.toArray()) {
                if (!candVotes.containsKey(cand))
                    candVotes.put(cand, 0);
            }

        }
    }

    public String computeWinner() {
        String winner = getWinner();        //Get the winner,

        if (isTied(winner) || winner.length() == 0) {    //If the winner is tied
            return "Tie";                    //return "Tie"
        } else {
            return winner;//Otherwise return the winner.
        }
    }

    protected abstract void setVotes();

    protected String getWinner() {
        String winner = "";
        int maxVotes = 0;

        for (Map.Entry<String, Integer> entry : candVotes.entrySet()) {
            if (entry.getValue() > maxVotes || maxVotes == 0) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }

        return winner;
    }

    protected boolean isTied(String candidate) {
        boolean isTie = false;
        int candsVotes = candVotes.get(candidate);

        for (Map.Entry<String, Integer> entry : candVotes.entrySet()) {

            if (entry.getValue() == candsVotes &&
                    !candidate.equals(entry.getKey())) {
                isTie = true; //Then that candidate is tied.
            }
        }

        return isTie;
    }

    public String results() {
        String results = "";

        String[] candidates = getSortedCandidateList(); //Sorts the candidates.

        for (String candidate : candidates) {
            results += String.format("%-18s %d", //each candidate's results.
                    candidate + ":", candVotes.get(candidate)) + "\n";
        }

        return results;
    }

    protected String[] getSortedCandidateList() {
        String[] candidates = new String[candVotes.size()];

        int i = 0;

        for (Map.Entry<String, Integer> entry : candVotes.entrySet())
            candidates[i++] = entry.getKey();

        insertionSort(candidates);

        return candidates;
    }

    private void insertionSort(String[] a) {
        for (int i = 1; i < a.length; i++) {
            String val = a[i];
            int index = i;

            while (index > 0 && candVotes.get(val) > candVotes.get(a[index - 1])) {
                a[index] = a[index - 1];
                index--;
            }

            a[index] = val;
        }
    }
}
