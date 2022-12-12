package vttp.paf.workshopDay26.repositories;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp.paf.workshopDay26.models.BoardGame;
import vttp.paf.workshopDay26.models.GameDetails;

@Repository
public class BoardGameRepository {
    
    public static final String C_GAME = "game";

    @Autowired
    private MongoTemplate mongoTemplate;

    /*
     * db.game.find({},{_id:0, gid:1, name:1}).limit(25).skip(0).sort({ranking:1});
     */

    public List<BoardGame> getAllGames() {

        List<BoardGame> games = new LinkedList<>();
        //Create the criteria
        //no criteria to create
        
        //Build the query
        Query query = new Query().limit(25).skip(0);
        query.fields().exclude("_id")
                    .include("gid", "name", "ranking");

        List<Document> results = mongoTemplate.find(query, Document.class, "game");
        
        //Build the list
        for(Document d: results) {
            games.add(BoardGame.create(d));
        }

        return games;
    }

    /*
     * 
     */

    public long getCount() {

        //create criteria
        //no criteria to create

        //build query
        Query query = new Query();
        long count = mongoTemplate.count(query, "game");
        return count;
    }

    /*
     * db.game.find({},{_id:0, gid:1, name:1}).limit(25).skip(0).sort({ranking:1});
     */
    public List<BoardGame> getGamesByRanking() {

        List<BoardGame> games = new LinkedList<>();
        //Create the criteria
        //no criteria to create
        
        //Build the query
        Query query = new Query()
                    .with(Sort.by(Sort.Direction.ASC, "ranking"))
                    .limit(25)
                    .skip(0);

        query.fields().exclude("_id")
                    .include("gid", "name", "ranking");

        List<Document> results = mongoTemplate.find(query, Document.class, "game");
        
        //Build the list
        for(Document d: results) {
            games.add(BoardGame.create(d));
        }

        return games;
    }

    /*
     * db.game.find({gid: ?});
     */
    
     public List<GameDetails> getGameDetailsById(int gid) {

        //Create empty list
        List<GameDetails> details = new LinkedList<>();
        //Build Criteria
        Criteria criteria = Criteria.where("gid").is(gid);
        //Build query
        Query query = Query.query(criteria);

        List<Document> result = mongoTemplate.find(query, Document.class, "game");

        //get the list
        for (Document d: result) {
            details.add(GameDetails.create(d));
        }
        return details;
     }
}
