package integration.es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.Main;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.models.UserFactory;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.UserQueryTypeSingle;
import es.uji.agdc.videoclub.services.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Alberto on 04/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class UserServiceDBTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = UserFactory.createMember()
                .setDni("10614397N")
                .setName("Paco Sánchez Díaz")
                .setAddress("C/Falsa, 123, 1º")
                .setPhone(693582471)
                .setEmail("pacosd@hotmail.com")
                .setBirthday(LocalDate.of(2016, 1, 20))
                .setUsername("paquito69")
                .setPassword("pacosd69");
    }

    @Test
    public void create() throws Exception {
        // Use the service to create a user
        service.create(user);

        // Fetch the user with the repository to assert that it is stored on the database
        Optional<User> possibleUser = repository.findByUsername("paquito69");

        // Assert that the user has been returned
        assertEquals(user.getName(), possibleUser.get().getName());
    }

    @Test
    public void findBy() throws Exception {
        // Use the service to create a user
        service.create(user);

        // Fetch the user with the service itself to assert that the service is able to access the database
        Optional<User> possibleUser = service.findBy(UserQueryTypeSingle.USERNAME, user.getUsername());

        // Assert that the user has been found
        assertTrue(possibleUser.isPresent());
        assertEquals(user.getUsername(), possibleUser.get().getUsername());
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

}