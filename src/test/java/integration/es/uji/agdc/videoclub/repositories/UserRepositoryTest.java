package integration.es.uji.agdc.videoclub.repositories;

import es.uji.agdc.videoclub.Main;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.models.utils.UserFactory;
import es.uji.agdc.videoclub.repositories.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by alberto on 2/12/16.
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class UserRepositoryTest {

    private static User user;

    @Autowired
    private UserRepository userRepository;

    @BeforeClass
    public static void setUp() throws Exception {
        user = UserFactory.createMember()
                .setDni("10614397N")
                .setName("Paco Sánchez Díaz")
                .setAddress("C/Falsa, 123, 1º")
                .setPhone(693582471)
                .setEmail("pacosd@hotmail.com")
                .setUsername("paquito69")
                .setPassword("pacosd69");
    }

    @Test
    public void createUser() throws Exception {
        User saved = userRepository.save(user);
        assertNotNull(saved.getId());
    }

    @Test
    public void findUserByDni() throws Exception {
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByDni(user.getDni());
        assertTrue(savedUser.isPresent());
    }

    @Test
    public void findUserByUsername() throws Exception {
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByUsername(user.getUsername());
        assertTrue(savedUser.isPresent());
    }

    @Test
    public void findUserByEmail() throws Exception {
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByEmail(user.getEmail());
        assertTrue(savedUser.isPresent());
    }

    @Test
    public void findUsersByRole() throws Exception {
        userRepository.save(user);
        Stream<User> members = userRepository.findByRole(User.Role.MEMBER);
        assertTrue(members.count() > 0);
    }

    @Test
    public void updateUser() throws Exception {
        User savedUser = userRepository.save(user);
        String newName = "Paco Sánchez García";
        savedUser.setName(newName);
        User modifiedUser = userRepository.save(savedUser);
        assertEquals(newName, modifiedUser.getName());
    }

    @Test
    public void deleteUser() throws Exception {
        User savedUser = userRepository.save(user);
        userRepository.delete(savedUser);
        Optional<User> noUser = userRepository.findByDni(user.getDni());
        assertFalse(noUser.isPresent());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        user = null;
    }
}