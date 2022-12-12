package vttp.paf.workshopDay26.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class GameDetails {
    private int gid;
    private String name;
    private int year;
    private int ranking;
    private int usersRated;
    private String url;
    private String image;

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
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public int getUsersRated() {
        return usersRated;
    }
    public void setUsersRated(int usersRated) {
        this.usersRated = usersRated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public static GameDetails create(Document doc) {
        final GameDetails game = new GameDetails();
        game.setGid(doc.getInteger("gid"));
        game.setName(doc.getString("name"));
        game.setYear(doc.getInteger("year"));
        game.setRanking(doc.getInteger("ranking"));
        game.setUsersRated(doc.getInteger("users_rated"));
        game.setUrl(doc.getString("url"));
        game.setImage(doc.getString("image"));
        return game;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("gid", gid)
            .add("name", name)
            .add("year", year)
            .add("ranking", ranking)
            .add("users_rated", usersRated)
            .add("url", url)
            .add("image", image)
            .build();
    }
    
}
