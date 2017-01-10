package es.uji.agdc.videoclub.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by alberto on 10/1/17.
 */
@Entity
@Table(name = "visualization_links")
public class VisualizationLink extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Movie movie;

    @Column(nullable = false)
    private String linkToken;

    @Column(nullable = false)
    private LocalDateTime expeditionDate;

    protected VisualizationLink() {
    }

    public VisualizationLink(User user, Movie movie) {
        setUser(user);
        setMovie(movie);
        linkToken = UUID.randomUUID().toString();
        expeditionDate = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public VisualizationLink setUser(User user) {
        this.user = user;
        user.addVisualizationLink(this);
        return this;
    }

    public Movie getMovie() {
        return movie;
    }

    public VisualizationLink setMovie(Movie movie) {
        this.movie = movie;
        movie.addVisualizationLink(this);
        return this;
    }

    public String getLinkToken() {
        return linkToken;
    }

    public VisualizationLink setLinkToken(String linkToken) {
        this.linkToken = linkToken;
        return this;
    }

    public LocalDateTime getExpeditionDate() {
        return expeditionDate;
    }

    public VisualizationLink setExpeditionDate(LocalDateTime expeditionDate) {
        this.expeditionDate = expeditionDate;
        return this;
    }

    @Override
    public String toString() {
        return "VisualizationLink{" +
                "user=" + user +
                ", movie=" + movie +
                ", linkToken='" + linkToken + '\'' +
                ", expeditionDate=" + expeditionDate +
                "} " + super.toString();
    }
}
