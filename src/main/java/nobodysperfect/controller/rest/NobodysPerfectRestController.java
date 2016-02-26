package nobodysperfect.controller.rest;

import nobodysperfect.domain.*;
import nobodysperfect.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.List;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@ComponentScan
@RestController
@RequestMapping("/api")
public class NobodysPerfectRestController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private GuessRepository guessRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> entityNotFound(EntityNotFoundException e) {
        Error error = new Error("Entity not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @ApiOperation(value = "getGames", nickname = "getGames")
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 500, message = "Failure")})
    public List<Game> games() {
        return gameRepository.findAll();
    }

    @ApiOperation(value = "getGame", nickname = "getGame")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Game.class),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/games/{game}")
    public ResponseEntity<Game> game(@PathVariable("game") String gameId) {
        Game game = gameRepository.findOne(gameId);
        if (game == null) { throw new EntityNotFoundException(); }
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @ApiOperation(value = "addGame", nickname = "addGame")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Redirect"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public void addGame(Game game, HttpServletResponse response) throws IOException {
        gameRepository.save(game);
        response.sendRedirect("/api/games/" + game.getId());
    }

    @RequestMapping(value = "/games/{game}", method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable("game") String gameId, HttpServletResponse response) throws IOException {
        Game game = gameRepository.findOne(gameId);
        if (game != null) {
            gameRepository.delete(game);
            response.setStatus(HttpStatus.OK.value());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

}
