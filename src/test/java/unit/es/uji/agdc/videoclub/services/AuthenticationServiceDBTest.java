package unit.es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.AuthenticationService;
import es.uji.agdc.videoclub.services.AuthenticationServiceDB;
import es.uji.agdc.videoclub.services.utils.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Alberto on 03/12/2016.
 */
public class AuthenticationServiceDBTest {

    private AuthenticationService authService;
    private UserRepository repository;
    private User user;

    @Before
    public void setUp() throws Exception {
        repository = mock(UserRepository.class);
        authService = new AuthenticationServiceDB(repository);
        user = new User()
                .setUsername("paquito69")
                .setPassword("pacosd69");

        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(repository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
    }

    @Test
    public void auth_rightUsernameAndPassword_ok() throws Exception {
        Result result = authService.auth(user.getUsername(), "pacosd69");
        assertTrue(result.isOk());
    }

    @Test
    public void auth_badUsername_usernameError() throws Exception {
        Result result = authService.auth("paquito", "pacosd69");
        assertEquals("Result: ERROR(LOGIN_FAILED)[USERNAME]", result.toString());
    }

    @Test
    public void auth_badUsername_passwordError() throws Exception {
        Result result = authService.auth(user.getUsername(), "pacosd6");
        assertEquals("Result: ERROR(LOGIN_FAILED)[PASSWORD]", result.toString());
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
        authService = null;
    }

}