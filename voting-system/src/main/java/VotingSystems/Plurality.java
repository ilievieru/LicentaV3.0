package VotingSystems;

public class Plurality extends VotingSystem {

    public Plurality() {
    }

    public Plurality(Ballot[] ballots) {
        super(ballots);
    }

    protected void setVotes() {
        for (Ballot ballot : voterBallots) {
            candVotes.put(ballot.getCandidate(0),
                    candVotes.get(ballot.getCandidate(0)) + 1);
        }
    }
}
