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
@Table(name = "genres")
public class Genre extends AbstractEntity {
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    protected Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Genre setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}
