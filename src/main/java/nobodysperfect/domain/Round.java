package nobodysperfect.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Round {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Integer round;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GAME_ID")
    private Game game;

    @OneToMany(mappedBy = "round")
    private List<Score> scores;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public List<Score> getScores() {
        return scores;
    }

}
