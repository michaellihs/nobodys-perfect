package nobodysperfect.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByRound(Round round);

    List<Score> findByPlayer(Player player);

}
