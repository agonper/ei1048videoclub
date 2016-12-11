package integration.es.uji.agdc.videoclub.repositories;

import es.uji.agdc.videoclub.Main;
import es.uji.agdc.videoclub.models.Actor;
import es.uji.agdc.videoclub.models.Director;
import es.uji.agdc.videoclub.models.Genre;
import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.repositories.MovieRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Alberto on 09/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class MovieRepositoryTest {

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
                    .addGenre(new Genre("Comedia"))
                .setDescription("Y, viéndole don Quijote de aquella manera, con muestras de tanta " +
                        "tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no " +
                        "hace más que otro. Todas estas borrascas que nos suceden son.")
                .setAvailableCopies(3);
    }

    @Test
    public void createMovie() throws Exception {
        Movie savedMovie = repository.save(movie);
        assertNotNull(savedMovie.getId());
        assertEquals(2, savedMovie.getActors().size());
        assertEquals(1, savedMovie.getDirectors().size());
        assertEquals(1, savedMovie.getGenres().size());
        assertEquals(movie.getDescription(), savedMovie.getDescription());
    }

    //TODO Find by title and year

    @Test
    public void modifyMovie() throws Exception {
        Movie savedMovie = repository.save(movie);
        savedMovie.addActor(new Actor("Sebastian Stan")).setAvailableCopies(4);
        Movie modifiedMovie = repository.save(savedMovie);
        assertEquals(3, modifiedMovie.getActors().size());
        assertEquals(4, modifiedMovie.getAvailableCopies());
    }

    //TODO Delete movie

    @After
    public void tearDown() throws Exception {
        movie = null;
    }

}