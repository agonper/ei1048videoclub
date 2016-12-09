package es.uji.agdc.videoclub.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Movie entity from the business domain, contains actors and directors
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
    private Set<Actor> actors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_directors")
    private Set<Director> directors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_genres")
    private Set<Genre> genres;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private int availableCopies;
}
