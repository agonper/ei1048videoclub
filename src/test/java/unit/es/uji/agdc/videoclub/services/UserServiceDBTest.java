package unit.es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.models.UserFactory;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.UserService;
import es.uji.agdc.videoclub.services.UserServiceDB;
import es.uji.agdc.videoclub.services.utils.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Alberto on 04/12/2016.
 */
public class UserServiceDBTest {

    private UserRepository repository;

    private UserService service;

    private User user;

    @Before
    public void setUp() throws Exception {
        repository = mock(UserRepository.class);
        service = new UserServiceDB(repository);
        user = new User()
                .setDni("10614397N")
                .setName("Paco Sánchez Díaz")
                .setAddress("C/Falsa, 123, 1º")
                .setPhone(693582471)
                .setEmail("pacosd@hotmail.com")
                .setBirthday(LocalDate.of(2016, 1, 20))
                .setUsername("paquito69")
                .setPassword("pacosd69")
                .setRole(User.Role.MEMBER);
    }

    @Test
    public void create_validUser_saveCalledAndReturnsOk() throws Exception {
        Result result = service.create(user);
        verify(repository, only()).save(user);
        assertTrue(result.isOk());
    }

    @Test
    public void create_oldUser_fails() throws Exception {
        User oldUser = mock(User.class);
        when(oldUser.getId()).thenReturn(new Long(0));
        Result result = service.create(oldUser);
        verify(repository, never()).save(user);
        assertTrue(result.isError());
        assertEquals("OLD_USER", result.getMsg());
    }

    //FIXME add more tests

    @After
    public void tearDown() throws Exception {
        repository = null;
        service = null;
        user = null;
    }

}