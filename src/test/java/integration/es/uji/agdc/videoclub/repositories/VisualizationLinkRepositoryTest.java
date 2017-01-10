package integration.es.uji.agdc.videoclub.repositories;

import es.uji.agdc.videoclub.Main;
import es.uji.agdc.videoclub.models.*;
import es.uji.agdc.videoclub.models.utils.UserFactory;
import es.uji.agdc.videoclub.repositories.VisualizationLinkRepository;
import es.uji.agdc.videoclub.services.MovieService;
import es.uji.agdc.videoclub.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by alberto on 10/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class VisualizationLinkRepositoryTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private VisualizationLinkRepository visualizationLinkRepository;

    private User user;
    private Movie movie;
    private VisualizationLink visualizationLink;

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

        movie = new Movie()
                .setTitle("Capitán América")
                .setTitleOv("Captain America")
                .setYear(2011)
                .addActor(new Actor("Chris Evans"))
                .addDirector(new Director("Joe Johnston"))
                .addGenre(new Genre("Acción"))
                .setDescription("Y, viéndole don Quijote de aquella manera, con muestras de tanta " +
                        "tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no " +
                        "hace más que otro. Todas estas borrascas que nos suceden son.")
                .setAvailableCopies(3);

        userService.create(user);
        movieService.create(movie);

        visualizationLink = new VisualizationLink(user, movie);

    }

    @Test
    public void createVisualizationLink() throws Exception {
        VisualizationLink savedVisualizationLink = visualizationLinkRepository.save(visualizationLink);
        assertNotNull(savedVisualizationLink.getId());
    }

    @Test
    public void findVisualizationLinkByToken() throws Exception {
        visualizationLinkRepository.save(visualizationLink);

        Optional<VisualizationLink> savedVisualizationLink =
                visualizationLinkRepository.findByToken(visualizationLink.getToken());

        assertTrue(savedVisualizationLink.isPresent());
    }

    @Test
    public void updateVisualizationLink() throws Exception {
        VisualizationLink savedVisualizationLink = visualizationLinkRepository.save(visualizationLink);

        LocalDateTime expeditionDate = LocalDateTime.now();
        savedVisualizationLink.setExpeditionDate(expeditionDate);
        VisualizationLink modifiedVisualizationLink = visualizationLinkRepository.save(savedVisualizationLink);

        System.out.println(modifiedVisualizationLink);

        assertEquals(expeditionDate, modifiedVisualizationLink.getExpeditionDate());
    }

    @Test
    public void deleteVisualizationLink() throws Exception {
        VisualizationLink savedVisualizationLink = visualizationLinkRepository.save(visualizationLink);
        visualizationLinkRepository.delete(savedVisualizationLink);
        Optional<VisualizationLink> noVisualizationLink =
                visualizationLinkRepository.findByToken(visualizationLink.getToken());
        assertFalse(noVisualizationLink.isPresent());
    }

    @After
    public void tearDown() throws Exception {
        user = null;
        movie = null;
        visualizationLink = null;
    }
}