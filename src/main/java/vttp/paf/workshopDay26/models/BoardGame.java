package vttp.paf.workshopDay26.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class BoardGame {

    private int gid;
    private String name;
    private int ranking;

    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static BoardGame create(Document doc) {
        final BoardGame game = new BoardGame();
        game.setGid(doc.getInteger("gid"));
        game.setName(doc.getString("name"));
        game.setRanking(doc.getInteger("ranking"));

        return game;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("gid", gid)
            .add("name", name)
            .add("ranking", ranking)
            .build();
    }

    

}
