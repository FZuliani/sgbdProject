package org.example.Models;

import java.util.List;

public class Films {
    private String id;
    private String title;
    private String releaseDate;
    private String duration;
    private String genre;
    private String director;
    private List<Actors> actors;
    private String description;

    //region Constructors

    public Films(String id, String title, String releaseDate, String duration, String genre, String director, List<Actors> actors, String description) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.description = description;
    }

    public Films() {
    }
    //endregion

    //region setter and getter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public List<Actors> getActors() {
        return actors;
    }
    public void setActors(List<Actors> actors) {
        this.actors = actors;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    //endregion


    @Override
    public String toString() {
        return "Films{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", duration='" + duration + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", actors=" + actors +
                ", description='" + description + '\'' +
                '}';
    }
}
