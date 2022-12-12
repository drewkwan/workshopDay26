package vttp.paf.workshopDay26.controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.paf.workshopDay26.models.BoardGame;
import vttp.paf.workshopDay26.repositories.BoardGameRepository;

@RestController
@RequestMapping(path="", produces=MediaType.APPLICATION_JSON_VALUE)
public class BoardGameRESTController {

    @Autowired
    private BoardGameRepository boardGameRepository;

    @GetMapping("/games")
    public ResponseEntity<String> getAllGames() {

        //Send the query
        List<BoardGame> results = boardGameRepository.getAllGames();

        long total = boardGameRepository.getCount();

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        //Build the resutls 
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (BoardGame b: results) {
            arrayBuilder.add(b.toJson());
        }

        JsonArray result = arrayBuilder.build();

        JsonObject response = Json.createObjectBuilder()
                    .add("games", result)
                    .add("offset", 0)
                    .add("limit", 25)
                    .add("total", total)
                    .add("timestamp", ts.toString())
                    .build();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }

    @GetMapping("/games/rank")
    public ResponseEntity<String> getGamesByRank() {
        //Send the query
        List<BoardGame> results = boardGameRepository.getGamesByRanking();

        long total = boardGameRepository.getCount();

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        //Build the resutls 
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (BoardGame b: results) {
            arrayBuilder.add(b.toJson());
        }

        JsonArray result = arrayBuilder.build();

        JsonObject response = Json.createObjectBuilder()
                    .add("games", result)
                    .add("offset", 0)
                    .add("limit", 25)
                    .add("total", total)
                    .add("timestamp", ts.toString())
                    .build();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }

    
}
