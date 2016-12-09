package es.uji.agdc.videoclub.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by alberto on 9/12/16.
 */
@Entity
@Table(name = "directors")
public class Director extends AbstractEntity {
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "directors")
    private Set<Movie> movies;

    protected Director() {
    }

    public Director(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Director setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}
