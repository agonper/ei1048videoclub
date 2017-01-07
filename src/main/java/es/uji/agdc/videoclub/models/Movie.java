package es.uji.agdc.videoclub.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Movie entity from the business domain, contains actors, directors and genres
 */
@Entity
@Table(name = "movies")
public class Movie extends AbstractEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String titleOv;

    @Column(nullable = false)
    private int year;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actors")
    private List<Actor> actors = new LinkedList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_directors")
    private List<Director> directors = new LinkedList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_genres")
    private List<Genre> genres = new LinkedList<>();

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

    public List<Actor> getActors() {

        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Movie addActor(Actor actor) {
        List<Actor> actors = getActors();
        actors.add(actor);
        return this;
    }

    public Movie addDirector(Director director) {
        List<Director> directors = getDirectors();
        directors.add(director);
        return this;
    }

    public Movie addGenre(Genre genre) {
        List<Genre> genres = getGenres();
        genres.add(genre);
        return this;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", titleOv='" + titleOv + '\'' +
                ", year=" + year +
                ", actors=" + actors +
                ", directors=" + directors +
                ", genres=" + genres +
                ", description='" + description + '\'' +
                ", availableCopies=" + availableCopies +
                "} " + super.toString();
    }
}
