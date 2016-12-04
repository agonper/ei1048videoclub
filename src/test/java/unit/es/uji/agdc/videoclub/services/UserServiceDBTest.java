package unit.es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.helpers.PasswordEncryptor;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.models.UserFactory;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.UserQueryTypeSingle;
import es.uji.agdc.videoclub.services.UserService;
import es.uji.agdc.videoclub.services.UserServiceDB;
import es.uji.agdc.videoclub.services.utils.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Alberto on 04/12/2016.
 */
public class UserServiceDBTest {

    private UserRepository repository;

    private PasswordEncryptor encryptor;

    private UserService service;

    private User user;

    @Before
    public void setUp() throws Exception {
        repository = mock(UserRepository.class);
        encryptor = mock(PasswordEncryptor.class);
        service = new UserServiceDB(repository, encryptor);
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
        String userPassword = user.getPassword();
        Result result = service.create(user);
        verify(encryptor, only()).hash(userPassword);
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

    @Test
    public void findBy_username_found() throws Exception {
        when(repository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.USERNAME, user.getUsername());
        assertTrue(possibleUser.isPresent());
    }

    @Test
    public void findBy_username_notFound() throws Exception {
        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.USERNAME, user.getUsername());
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_username_nullValueReturnsEmpty() throws Exception {
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.USERNAME, null);
        verify(repository, never()).findByUsername(null);
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_username_emptyStringReturnsEmpty() throws Exception {
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.USERNAME, "");
        verify(repository, never()).findByUsername("");
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_id_found() throws Exception {
        when(repository.findOne(0L)).thenReturn(Optional.of(user));
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.ID, Long.toString(0));
        assertTrue(possibleUser.isPresent());
    }

    @Test
    public void findBy_id_notFound() throws Exception {
        when(repository.findOne(anyLong())).thenReturn(Optional.empty());
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.ID, Long.toString(0));
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_id_nullValueReturnsEmpty() throws Exception {
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.ID, null);
        verify(repository, never()).findOne(null);
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_id_emptyStringReturnsEmpty() throws Exception {
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.ID, "");
        verify(repository, never()).findOne(any());
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_dni_found() throws Exception {
        when(repository.findByDni(user.getDni())).thenReturn(Optional.of(user));
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.DNI, user.getDni());
        assertTrue(possibleUser.isPresent());
    }

    @Test
    public void findBy_dni_notFound() throws Exception {
        when(repository.findByDni(anyString())).thenReturn(Optional.empty());
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.DNI, user.getDni());
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_dni_nullValueReturnsEmpty() throws Exception {
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.DNI, null);
        verify(repository, never()).findByDni(null);
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_dni_emptyStringReturnsEmpty() throws Exception {
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.DNI, "");
        verify(repository, never()).findByDni(any());
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_email_found() throws Exception {
        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.EMAIL, user.getEmail());
        assertTrue(possibleUser.isPresent());
    }

    @Test
    public void findBy_email_notFound() throws Exception {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.EMAIL, user.getEmail());
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_email_nullValueReturnsEmpty() throws Exception {
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.EMAIL, null);
        verify(repository, never()).findByEmail(null);
        assertTrue(!possibleUser.isPresent());
    }

    @Test
    public void findBy_email_emptyStringReturnsEmpty() throws Exception {
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.EMAIL, "");
        verify(repository, never()).findByEmail(any());
        assertTrue(!possibleUser.isPresent());
    }

    //FIXME Add more tests

    @After
    public void tearDown() throws Exception {
        repository = null;
        service = null;
        user = null;
    }

}