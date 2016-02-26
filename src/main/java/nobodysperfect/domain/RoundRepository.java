package nobodysperfect.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round, Long> {

    List<Round> findByGame(Game game);

}
