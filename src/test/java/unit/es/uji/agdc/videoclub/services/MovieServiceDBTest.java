package unit.es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.Actor;
import es.uji.agdc.videoclub.models.Director;
import es.uji.agdc.videoclub.models.Genre;
import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.repositories.MovieRepository;
import es.uji.agdc.videoclub.services.*;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.validators.MovieValidator;
import es.uji.agdc.videoclub.validators.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Alberto on 11/12/2016.
 */
public class MovieServiceDBTest {

    private MovieService service;
    private MovieRepository movieRepository;
    private MovieAssetService assetService;

    private Movie movie;

    @Before
    public void setUp() throws Exception {
        movieRepository = mock(MovieRepository.class);
        assetService = mock(MovieAssetService.class);
        Validator<Movie> validator = new MovieValidator();
        service = new MovieServiceDB(movieRepository, assetService, validator);
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

        when(movieRepository.save(any(Movie.class))).thenReturn(new Movie());

        when(movieRepository.findByTitleIgnoreCaseAndYear(movie.getTitle(), movie.getYear()))
                .thenReturn(Optional.empty());
        when(movieRepository.findAll()).thenReturn(Stream.empty());

        when(assetService.findActorByName(anyString())).thenReturn(Optional.empty());
        when(assetService.findDirectorByName(anyString())).thenReturn(Optional.empty());
        when(assetService.findGenreByName(anyString())).thenReturn(Optional.empty());
    }

    @Test
    public void create_nonExistingValidMovie_returnsOk() throws Exception {
        Result result = service.create(movie);

        verify(movieRepository, times(1))
                .findByTitleIgnoreCaseAndYear(movie.getTitle(), movie.getYear());
        verify(movieRepository, times(1)).save(movie);

        assertTrue(result.isOk());
    }


    @Test
    public void create_existingMovie_returnsError() throws Exception {
        when(movieRepository.findByTitleIgnoreCaseAndYear(movie.getTitle(), movie.getYear()))
                .thenReturn(Optional.of(movie));

        Result result = service.create(movie);

        verify(movieRepository, never()).save(movie);

        assertTrue(result.isError());
        assertEquals("MOVIE_ALREADY_EXISTS", result.getMsg());
    }

    @Test
    public void create_movieIsNotNew_returnError() throws Exception {
        Movie movie = mock(Movie.class);
        when(movie.getId()).thenReturn(Long.valueOf(1));

        Result result = service.create(movie);
        verify(movieRepository, never())
                .findByTitleIgnoreCaseAndYear(anyString(), anyInt());
        verify(movieRepository, never()).save(movie);

        assertTrue(result.isError());
        assertEquals("MOVIE_ALREADY_EXISTS", result.getMsg());
    }

    @Test
    public void create_invalidMovie_returnError() throws Exception {
        movie.setDescription("");

        Result result = service.create(movie);
        verify(movieRepository, never())
                .findByTitleIgnoreCaseAndYear(anyString(), anyInt());
        verify(movieRepository, never()).save(movie);

        assertTrue(result.isError());
        assertEquals("Result: ERROR(EMPTY_PARAMETER)[Description]", result.toString());
    }

    @Test
    public void create_addsExistingActor_returnsOk() throws Exception {
        Actor actor = new Actor("Chris Evans".toLowerCase());
        when(assetService.findActorByName("Chris Evans")).thenReturn(Optional.of(actor));
        service.create(movie);
        assertTrue(movie.getActors().stream().anyMatch(actor1 -> actor1.getName().equals(actor.getName())));
    }

    @Test
    public void create_addsMultipleExistingActors_returnsOk() throws Exception {
        Actor actor1 = new Actor("Chris Evans".toLowerCase());
        Actor actor2 = new Actor("Hayley Atwell".toLowerCase());
        when(assetService.findActorByName("Chris Evans")).thenReturn(Optional.of(actor1));
        when(assetService.findActorByName("Hayley Atwell")).thenReturn(Optional.of(actor2));
        service.create(movie);
        assertTrue(movie.getActors().stream()
                .filter(actor -> actor.getName().equals(actor1.getName()) || actor.getName().equals(actor2.getName()))
                .count() == 2
        );
    }

    @Test
    public void create_addsExistingDirector_returnsOk() throws Exception {
        Director director = new Director("Joe Johnston".toLowerCase());
        when(assetService.findDirectorByName("Joe Johnston")).thenReturn(Optional.of(director));
        service.create(movie);
        assertTrue(movie.getDirectors().stream().anyMatch(director1 -> director1.getName().equals(director.getName())));
    }

    @Test
    public void create_addsMultipleExistingDirectors_returnsOk() throws Exception {
        movie.addDirector(new Director("Director 2"));
        Director director1 = new Director("Joe Johnston".toLowerCase());
        Director director2 = new Director("Director 2".toLowerCase());

        when(assetService.findDirectorByName("Joe Johnston")).thenReturn(Optional.of(director1));
        when(assetService.findDirectorByName("Director 2")).thenReturn(Optional.of(director2));
        service.create(movie);
        assertTrue(movie.getDirectors().stream()
                .filter(director -> director.getName().equals(director1.getName()) || director.getName().equals(director2.getName()))
                .count() == 2
        );
    }

    @Test
    public void create_addsExistingGenre_returnsOk() throws Exception {
        Genre genre = new Genre("Comedy".toLowerCase());
        when(assetService.findGenreByName("Comedy")).thenReturn(Optional.of(genre));
        service.create(movie);
        assertTrue(movie.getGenres().stream().anyMatch(genre1 -> genre1.getName().equals(genre.getName())));
    }

    @Test
    public void create_addsMultipleExistingGenres_returnsOk() throws Exception {
        Genre genre1 = new Genre("Comedy".toLowerCase());
        Genre genre2 = new Genre("Drama".toLowerCase());
        when(assetService.findGenreByName("Comedy")).thenReturn(Optional.of(genre1));
        when(assetService.findGenreByName("Drama")).thenReturn(Optional.of(genre2));
        service.create(movie);
        assertTrue(movie.getGenres().stream()
                .filter(genre -> genre.getName().equals(genre1.getName()) || genre.getName().equals(genre2.getName()))
                .count() == 2
        );
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

    @Test
    public void findBy_withNoValues_returnsEmpty() throws Exception {
        Optional<Movie> possibleMovie = service.findBy(MovieQueryTypeSingle.ID);
        assertFalse(possibleMovie.isPresent());
    }

    @Test
    public void findBy_idWithValidLong_returnsMovie() throws Exception {
        when(movieRepository.findOne(0L)).thenReturn(Optional.of(movie));
        Optional<Movie> possibleMovie = service.findBy(MovieQueryTypeSingle.ID, String.valueOf(0));
        assertTrue(possibleMovie.isPresent());
    }

    @Test
    public void findBy_idWithEmptyLong_returnsEmpty() throws Exception {
        Optional<Movie> possibleMovie = service.findBy(MovieQueryTypeSingle.ID);
        verify(movieRepository, never()).findOne(anyLong());
        assertFalse(possibleMovie.isPresent());
    }

    @Test
    public void findBy_idWithNonValidLong_returnsEmpty() throws Exception {
        Optional<Movie> possibleMovie = service.findBy(MovieQueryTypeSingle.ID, "blabla");
        verify(movieRepository, never()).findOne(anyLong());
        assertFalse(possibleMovie.isPresent());
    }

    @Test
    public void findBy_titleAndYearWithValidTitleAndYear_returnsMovie() throws Exception {
        when(movieRepository.findByTitleIgnoreCaseAndYear(movie.getTitle(), movie.getYear()))
                .thenReturn(Optional.of(movie));
        Optional<Movie> possibleMovie =
                service.findBy(MovieQueryTypeSingle.TITLE_AND_YEAR, movie.getTitle(), String.valueOf(movie.getYear()));
        assertTrue(possibleMovie.isPresent());
    }

    @Test
    public void findBy_titleAndYearWithEmptyYear_returnsEmpty() throws Exception {
        Optional<Movie> possibleMovie =
                service.findBy(MovieQueryTypeSingle.TITLE_AND_YEAR, movie.getTitle());
        verify(movieRepository, never()).findByTitleIgnoreCaseAndYear(anyString(), anyInt());
        assertFalse(possibleMovie.isPresent());
    }

    @Test
    public void findBy_titleAndYearWithInvalidYear_returnsEmpty() throws Exception {
        Optional<Movie> possibleMovie =
                service.findBy(MovieQueryTypeSingle.TITLE_AND_YEAR, movie.getTitle(), "dos mil once");
        verify(movieRepository, never()).findByTitleIgnoreCaseAndYear(anyString(), anyInt());
        assertFalse(possibleMovie.isPresent());
    }

    @Test
    public void findAllBy_withNoTextValue_returnsEmptyStream() throws Exception {
        Stream<Movie> allMoviesByNothing = service.findAllBy(MovieQueryTypeMultiple.ALL, "");

        assertEquals(0, allMoviesByNothing.count());
    }

    @Test
    public void findAllBy_withNoWordsInTextValue_returnsEmptyStream() throws Exception {
        Stream<Movie> allMoviesByNothing = service.findAllBy(MovieQueryTypeMultiple.ALL, " ");

        assertEquals(0, allMoviesByNothing.count());
    }

    @Test
    public void findAllBy_titleWithOneWord_returnsAMovie() throws Exception {
        movie.setId(0L);
        String[] titleWords = movie.getTitle().split(" ");
        Arrays.stream(titleWords).forEach(word ->
                when(movieRepository.findByTitleContainsIgnoreCase(word)).thenReturn(Stream.of(movie)));

        Stream<Movie> movies = service.findAllBy(MovieQueryTypeMultiple.TITLE, titleWords[0]);

        verify(movieRepository, times(1)).findByTitleContainsIgnoreCase(titleWords[0]);
        assertEquals(movie.getTitle(), movies.findFirst().get().getTitle());
    }

    @Test
    public void findAllBy_titleWithAllWords_returnsAMovie() throws Exception {
        movie.setId(0L);
        String[] titleWords = movie.getTitle().split(" ");
        Arrays.stream(titleWords).forEach(word ->
                when(movieRepository.findByTitleContainsIgnoreCase(word)).thenReturn(Stream.of(movie)));

        Stream<Movie> movies = service.findAllBy(MovieQueryTypeMultiple.TITLE, movie.getTitle());

        Arrays.stream(titleWords).forEach(word ->
                verify(movieRepository, times(1)).findByTitleContainsIgnoreCase(word));

        List<Movie> movieList = movies.collect(Collectors.toList());
        assertEquals(movie.getTitle(), movieList.get(0).getTitle());
        assertEquals(1, movieList.size());
    }

    @Test
    public void findAllBy_multipleMoviesWithSameTitleMatching_returnsBothMovies() throws Exception {
        String[] titleWords = movie.getTitle().split(" ");
        movie.setId(0L);

        Movie anotherMovie = new Movie().setTitle(movie.getTitle());
        anotherMovie.setId(1L);

        Arrays.stream(titleWords).forEach(word ->
                when(movieRepository.findByTitleContainsIgnoreCase(word)).thenReturn(Stream.of(movie, anotherMovie)));

        Stream<Movie> movies = service.findAllBy(MovieQueryTypeMultiple.TITLE, titleWords[0]);

        verify(movieRepository, times(1)).findByTitleContainsIgnoreCase(titleWords[0]);
        assertEquals(2, movies.count());
    }

    @Test
    public void findAllBy_multipleMoviesWithDifferentTitleMatching_returnsBothMoviesSortedByDescendingMatchNumber() throws Exception {
        String[] titleWords = movie.getTitle().split(" ");
        movie.setId(0L);

        Movie anotherMovie = new Movie().setTitle(titleWords[0]);
        anotherMovie.setId(1L);


        when(movieRepository.findByTitleContainsIgnoreCase(titleWords[0])).thenReturn(Stream.of(movie, anotherMovie));
        when(movieRepository.findByTitleContainsIgnoreCase(titleWords[1])).thenReturn(Stream.of(movie));

        Stream<Movie> movies = service.findAllBy(MovieQueryTypeMultiple.TITLE,
                String.format("%s %s", titleWords[0], titleWords[1]));

        verify(movieRepository, times(1)).findByTitleContainsIgnoreCase(titleWords[0]);
        verify(movieRepository, times(1)).findByTitleContainsIgnoreCase(titleWords[1]);
        assertEquals(movie.getTitle(), movies.findFirst().get().getTitle());
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        movieRepository = null;
        assetService = null;
        movie = null;
    }

}