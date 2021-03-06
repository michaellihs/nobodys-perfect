package nobodysperfect.controller.rest;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nobodysperfect.domain.Game;
import nobodysperfect.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class GamesController extends RestController {

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
    public Game game(@PathVariable("game") String gameId) {
        Game game = gameRepository.findOne(gameId);
        if (game == null) { throw new EntityNotFoundException(); }
        return game;
    }


    /**
     * In order to make this method call work with Postman, use the following "parameter":
     *
     * URL:                http://localhost:8080/api/games
     * Headers:            Content-Type application/json
     * raw post content:   {
     *                       "title" : "title",
     *                       "description" : "description"
     *                     }
     */
    @ApiOperation(value = "addGame", nickname = "addGame")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Redirect"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(
            value = "/games",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Game> addGame(@RequestBody Game game, UriComponentsBuilder uriComponentsBuilder, HttpServletResponse response) throws IOException {
        gameRepository.save(game);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = uriComponentsBuilder.path("/api/games/")
                                              .path(String.valueOf(game.getId()))
                                              .build()
                                              .toUri();

        headers.setLocation(locationUri);

        return new ResponseEntity<>(game, headers, HttpStatus.CREATED);
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
