package VotingSystems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstantRunoff extends VotingSystem {

    private boolean tied = false;

    private Map<String, String> results;

    private int remainingCandidates;

    public InstantRunoff() {
    }

    List<String> eliminated;

    public InstantRunoff(Ballot[] ballots) {
        initBallots(ballots);
        initCandidates(ballots);

        remainingCandidates = candVotes.size();
        eliminated = new ArrayList<String>();

        results = new HashMap<String, String>();

        for (Map.Entry<String, Integer> entry : candVotes.entrySet())
            results.put(entry.getKey(), "");

        setVotes();
    }

    @Override
    protected void setVotes() {
        initializeVotes();

        for (Ballot ballot : voterBallots) {
            candVotes.put(ballot.getCandidate(0),
                    candVotes.get(ballot.getCandidate(0)) + 1);
        }

        updateResults();
        runoff();
    }

    public String results() {
        String totalResults = "";

        String[] candidates = getSortedCandidateList(); //Sorts the candidates.

        for (String candidate : candidates) {
            totalResults += String.format("%-18s", candidate) +
                    results.get(candidate) + "\n";
        }

        return totalResults;
    }

    private void initializeVotes() {
        for (Map.Entry<String, Integer> entry : candVotes.entrySet())
            entry.setValue(0);
    }

    private void runoff() {
        if (candVotes.get(getWinner()) > voterBallots.size() / 2 ||
                remainingCandidates < 2 || tied)
            return;

        if (!isTied(getLowestCandidate())) {
            eliminate(getLowestCandidate());
            setVotes();
        }
    }

    private String getLowestCandidate() {
        String lowest = "";
        int fewestVotes = 0;

        for (Map.Entry<String, Integer> entry : candVotes.entrySet()) {
            if ((entry.getValue() < fewestVotes || lowest.length() == 0) &&
                    !eliminated.contains(entry.getKey())) {
                lowest = entry.getKey();
                fewestVotes = entry.getValue();
            }
        }

        return lowest;
    }

    private void eliminate(String candidate) {
        for (Ballot ballot : voterBallots)
            ballot.eliminateCandidate(candidate);

        eliminated.add(candidate);
        remainingCandidates--;
    }

    private void updateResults() {
        for (Map.Entry<String, String> entry : results.entrySet())
            entry.setValue(entry.getValue() + "\t "
                    + candVotes.get(entry.getKey()));
    }

}
