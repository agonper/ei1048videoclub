package es.uji.agdc.videoclub.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Movie entity from the business domain, contains actors, directors and genres
 */
@Entity
@Table(name = "movies")
public class Movie extends AbstractEntity{
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String titleOv;

    @Column(nullable = false)
    private int year;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actors")
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_directors")
    private Set<Director> directors = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_genres")
    private Set<Genre> genres = new HashSet<>();

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private int availableCopies;

    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitleOv() {
        return titleOv;
    }

    public Movie setTitleOv(String titleOv) {
        this.titleOv = titleOv;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Movie setYear(int year) {
        this.year = year;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Movie setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public Movie setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
        return this;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<Actor> addActor(Actor actor) {
        Set<Actor> actors = getActors();
        actors.add(actor);
        return actors;
    }

    public Set<Director> addDirector(Director director) {
        Set<Director> directors = getDirectors();
        directors.add(director);
        return directors;
    }

    public Set<Genre> addGenre(Genre genre) {
        Set<Genre> genres = getGenres();
        genres.add(genre);
        return genres;
    }
}
