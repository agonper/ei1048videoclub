package integration.es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.Main;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.AuthenticationService;
import es.uji.agdc.videoclub.services.utils.Result;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by Alberto on 03/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class AuthenticationServiceDBTest {
    Logger log = LoggerFactory.getLogger(AuthenticationServiceDBTest.class);

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private UserRepository repository;

    private static User user;

    @BeforeClass
    public static void setUp() throws Exception {
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
    public void auth() throws Exception {
        // Create user first
        repository.save(user); //FIXME: Employ UserService instead
        log.warn("Using repository on integration test, use service instead");

        // Try to authenticate user
        Result result = authService.auth(user.getUsername(), "pacosd69");

        // Assert that the user was successfully authenticated
        assertTrue(result.isOk());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        user = null;
    }
}