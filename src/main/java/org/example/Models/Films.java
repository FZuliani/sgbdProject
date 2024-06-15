package org.example.Models;

import java.util.List;

public class Films {
    public String id;
    public String title;
    public String releaseDate;
    public String duration;
    public String genre;
    public String director;
    public List<Actors> actors;
    public String description;

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
        StringBuilder actorsString = new StringBuilder();
        if(this.actors != null)
            for (Actors actor : this.actors) {
                actorsString.append(actor.toString());
            }
        return "Films{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", duration='" + duration + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", actors= [" + actorsString + "]" +
                ", description='" + description + '\'' +
                '}';
    }
}
