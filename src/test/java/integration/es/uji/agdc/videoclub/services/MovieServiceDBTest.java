package integration.es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.Main;
import es.uji.agdc.videoclub.models.Actor;
import es.uji.agdc.videoclub.models.Director;
import es.uji.agdc.videoclub.models.Genre;
import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.repositories.MovieRepository;
import es.uji.agdc.videoclub.services.MovieQueryTypeSingle;
import es.uji.agdc.videoclub.services.MovieService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by Alberto on 11/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class MovieServiceDBTest {
    @Autowired
    private MovieService service;

    @Autowired
    private MovieRepository repository;

    private Movie movie;

    @Before
    public void setUp() throws Exception {
        movie = new Movie()
                .setTitle("Capitán América")
                .setTitleOv("Captain America")
                .setYear(2011)
                .addActor(new Actor("Chris Evans"))
                .addActor(new Actor("Hayley Atwell"))
                .addDirector(new Director("Joe Johnston"))
                .addGenre(new Genre("Comedy"))
                .addGenre(new Genre("Drama"))
                .setDescription("Y, viéndole don Quijote de aquella manera, con muestras de tanta " +
                        "tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no " +
                        "hace más que otro. Todas estas borrascas que nos suceden son.")
                .setAvailableCopies(3);
    }

    @Test
    public void create() throws Exception {
        service.create(movie);
        Optional<Movie> possibleMovie = repository.findByTitleIgnoreCaseAndYear(movie.getTitle(), movie.getYear());
        assertTrue(possibleMovie.isPresent());
        Movie recoveredMovie = possibleMovie.get();
        assertTrue(!recoveredMovie.isNew());
        assertEquals(this.movie.getTitle(), recoveredMovie.getTitle());
        assertEquals(this.movie.getTitleOv(), recoveredMovie.getTitleOv());
        assertEquals(this.movie.getActors().size(), recoveredMovie.getActors().size());
        assertEquals(this.movie.getDirectors().size(), recoveredMovie.getDirectors().size());
        assertEquals(this.movie.getGenres().size(), recoveredMovie.getGenres().size());
        assertEquals(this.movie.getDescription(), recoveredMovie.getDescription());
        assertEquals(this.movie.getDescription(), recoveredMovie.getDescription());
    }

    @Test
    public void findBy() throws Exception {
        service.create(movie);
        Optional<Movie> possibleMovie =
                service.findBy(MovieQueryTypeSingle.TITLE_AND_YEAR, movie.getTitle(), String.valueOf(movie.getYear()));
        assertTrue(possibleMovie.isPresent());
    }

    @Test
    public void findAll() throws Exception {
        service.create(movie);
        Stream<Movie> allMovies = service.findAll();
        assertTrue(allMovies.count() > 0);
    }


    @After
    public void tearDown() throws Exception {
        movie = null;
    }

}