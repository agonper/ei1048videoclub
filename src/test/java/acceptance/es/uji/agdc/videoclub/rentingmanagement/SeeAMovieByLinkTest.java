package acceptance.es.uji.agdc.videoclub.rentingmanagement;

import es.uji.agdc.videoclub.Main;
import es.uji.agdc.videoclub.models.*;
import es.uji.agdc.videoclub.models.utils.UserFactory;
import es.uji.agdc.videoclub.services.MovieService;
import es.uji.agdc.videoclub.services.UserService;
import es.uji.agdc.videoclub.services.VisualizationLinkService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * Created by Alberto on 10/01/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class SeeAMovieByLinkTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private VisualizationLinkService linkService;

    private User user;
    private Movie movieOne;
    private Movie movieTwo;

    @Before
    public void setUp() throws Exception {
        user = UserFactory.createMember()
                .setDni("10614397N")
                .setName("Paco Sánchez Díaz")
                .setAddress("C/Falsa, 123, 1º")
                .setPhone(693582471)
                .setEmail("pacosd@hotmail.com")
                .setUsername("paquito69")
                .setPassword("pacosd69");

        movieOne = new Movie()
                .setTitle("Capitán América")
                .setTitleOv("Captain America")
                .setYear(2011)
                .addActor(new Actor("Hayley Atwell"))
                .addDirector(new Director("Joe Johnston"))
                .addGenre(new Genre("Acción"))
                .setDescription("Y, viéndole don Quijote de aquella manera, con muestras de tanta " +
                        "tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no " +
                        "hace más que otro. Todas estas borrascas que nos suceden son.")
                .setAvailableCopies(6);

        movieTwo = new Movie()
                .setTitle("Star Wars VII")
                .setTitleOv("Star Wars VII")
                .setYear(2015)
                .addActor(new Actor("Chris Evans"))
                .addDirector(new Director("Joe Johnston"))
                .addGenre(new Genre("Drama"))
                .setDescription("Y, viéndole don Quijote de aquella manera, con muestras de tanta " +
                        "tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no " +
                        "hace más que otro. Todas estas borrascas que nos suceden son.")
                .setAvailableCopies(6);

    }

    @Test
    public void name() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        user = null;
        movieOne = null;
        movieTwo = null;
    }
}
