package nobodysperfect.domain;

import javax.persistence.*;

@Entity
public class Guess {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ROUND_ID")
    private Round round;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PLAYER_ID")
    private Player player;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROPOSAL_ID")
    private Proposal selectedProposal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Proposal getSelectedProposal() {
        return selectedProposal;
    }

    public void setSelectedProposal(Proposal selectedProposal) {
        this.selectedProposal = selectedProposal;
    }
}
