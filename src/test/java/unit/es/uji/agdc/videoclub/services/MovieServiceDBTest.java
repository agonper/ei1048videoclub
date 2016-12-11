package unit.es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.Actor;
import es.uji.agdc.videoclub.models.Director;
import es.uji.agdc.videoclub.models.Genre;
import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.repositories.MovieRepository;
import es.uji.agdc.videoclub.services.MovieService;
import es.uji.agdc.videoclub.services.MovieServiceDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Alberto on 11/12/2016.
 */
public class MovieServiceDBTest {

    private MovieService service;
    private MovieRepository movieRepository;

    private Movie movie;

    @Before
    public void setUp() throws Exception {
        movieRepository = mock(MovieRepository.class);
        service = new MovieServiceDB(movieRepository);
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

        when(movieRepository.findAll()).thenReturn(Stream.empty());
    }

    @Test
    public void findAll_empty() throws Exception {
        Stream<Movie> allMovies = service.findAll();
        assertEquals(0, allMovies.count());
    }

    @Test
    public void findAll_nonEmpty() throws Exception {
        when(movieRepository.findAll()).thenReturn(Stream.of(movie));
        Stream<Movie> allMovies = service.findAll();
        assertEquals(1, allMovies.count());
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        movieRepository = null;
        movie = null;
    }

}