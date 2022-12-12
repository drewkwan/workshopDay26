package vttp.paf.workshopDay26.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp.paf.workshopDay26.models.GameDetails;
import vttp.paf.workshopDay26.repositories.BoardGameRepository;

@RestController
@RequestMapping(path="/game", produces=MediaType.APPLICATION_JSON_VALUE)
public class GameDetailsRESTController {

    @Autowired
    private BoardGameRepository boardGameRepository;

    @GetMapping(value="/{gameId}")
    public ResponseEntity<String> getGameDetails(@PathVariable int gameId) {

        List<GameDetails> results = boardGameRepository.getGameDetailsById(gameId);

        if (results.isEmpty()){
            String error = "Oops! There is no game with the id %d\n".formatted(gameId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(error);
        } else {
            //Build result 
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (GameDetails g: results) {
                jsonArrayBuilder.add(g.toJson());
            }

            JsonArray result = jsonArrayBuilder.build();
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }
    }
    
}
