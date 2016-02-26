package nobodysperfect.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuessRepository extends JpaRepository<Guess, Long> {

    List<Proposal> findByRound(Round round);

}
