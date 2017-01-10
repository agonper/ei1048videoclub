package unit.es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.models.VisualizationLink;
import es.uji.agdc.videoclub.repositories.VisualizationLinkRepository;
import es.uji.agdc.videoclub.services.*;
import es.uji.agdc.videoclub.services.utils.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Alberto on 10/01/2017.
 */
public class VisualizationLinkServiceDBTest {

    private UserService userService;
    private MovieService movieService;
    private VisualizationLinkRepository repository;

    private VisualizationLinkService service;

    private User user;
    private Movie movie;
    private VisualizationLink link;

    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
        movieService = mock(MovieService.class);
        repository = mock(VisualizationLinkRepository.class);

        service = new VisualizationLinkServiceDB(userService, movieService, repository);

        when(userService.findBy(any(), anyString())).thenReturn(Optional.empty());
        when(movieService.findBy(any(), anyString())).thenReturn(Optional.empty());

        user = new User();
        user.setId(0L);
        when(userService.findBy(UserQueryTypeSingle.ID, "0")).thenReturn(Optional.of(user));

        movie = new Movie();
        movie.setId(0L);
        movie.setAvailableCopies(1);
        when(movieService.findBy(MovieQueryTypeSingle.ID, "0")).thenReturn(Optional.of(movie));

        link = new VisualizationLink(user, movie);
    }

    @Test
    public void create_validVisualizationLink_returnsOk() throws Exception {
        Result result = service.create(link);

        verify(userService, only()).findBy(UserQueryTypeSingle.ID, user.getId().toString());
        verify(movieService, only()).findBy(MovieQueryTypeSingle.ID, movie.getId().toString());
        assertTrue(result.isOk());
    }

    @Test
    public void create_newUser_returnsError() throws Exception {
        user.setId(null);
        link.setUser(user);

        Result result = service.create(link);

        verify(userService, never()).findBy(any(), anyString());
        assertTrue(result.isError());
        assertEquals("USER_NOT_FOUND", result.getMsg());
    }

    @Test
    public void create_nonExistingUser_returnsError() throws Exception {
        user.setId(1L);
        link.setUser(user);

        Result result = service.create(link);

        verify(userService, only()).findBy(UserQueryTypeSingle.ID, "1");
        assertTrue(result.isError());
        assertEquals("USER_NOT_FOUND", result.getMsg());
    }

    @Test
    public void create_newMovie_returnsError() throws Exception {
        movie.setId(null);
        link.setMovie(movie);

        Result result = service.create(link);

        verify(movieService, never()).findBy(any(), anyString());
        assertTrue(result.isError());
        assertEquals("MOVIE_NOT_FOUND", result.getMsg());
    }

    @Test
    public void create_nonExistingMovie_returnsError() throws Exception {
        movie.setId(1L);
        link.setMovie(movie);

        Result result = service.create(link);

        verify(movieService, only()).findBy(MovieQueryTypeSingle.ID, "1");
        assertTrue(result.isError());
        assertEquals("MOVIE_NOT_FOUND", result.getMsg());
    }

    @Test
    public void create_nullExpeditionDate_returnsError() throws Exception {
        link.setExpeditionDate(null);
        Result result = service.create(link);

        assertTrue(result.isError());
        assertEquals("INVALID_EXPEDITION_DATE", result.getMsg());
    }

    @Test
    public void create_invalidExpeditionDate_returnsError() throws Exception {
        link.setExpeditionDate(LocalDateTime.now().plusSeconds(1));
        Result result = service.create(link);

        assertTrue(result.isError());
        assertEquals("INVALID_EXPEDITION_DATE", result.getMsg());
    }

    @Test
    public void create_movieWithNoCopies() throws Exception {
        /*movie.setAvailableCopies(0);
        Result result = service.create(link);

        assertTrue(result.isError());
        assertEquals("NO_COPIES_AVAILABLE", result.getMsg());*/
    }

    @After
    public void tearDown() throws Exception {
        userService = null;
        movieService = null;
        repository = null;
        service = null;

        user = null;
        movie = null;
        link = null;
    }

}