package nobodysperfect.controller.rest;

import nobodysperfect.domain.*;
import nobodysperfect.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class RestController {

    @Autowired
    protected GameRepository gameRepository;

    @Autowired
    protected PlayerRepository playerRepository;

    @Autowired
    protected RoundRepository roundRepository;

    @Autowired
    protected GuessRepository guessRepository;

    @Autowired
    protected ProposalRepository proposalRepository;

    @Autowired
    protected ScoreRepository scoreRepository;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Error entityNotFound(EntityNotFoundException e) {
        return new Error("Entity not found");
    }

}
