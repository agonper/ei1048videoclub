package es.uji.agdc.videoclub.initializers;

import es.uji.agdc.videoclub.helpers.Services;
import es.uji.agdc.videoclub.models.*;
import es.uji.agdc.videoclub.models.utils.UserFactory;
import es.uji.agdc.videoclub.services.MovieService;
import es.uji.agdc.videoclub.services.UserQueryTypeSingle;
import es.uji.agdc.videoclub.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Alberto on 04/12/2016.
 */
@Component
public class AdminInitializer {
    public static final String
            ADMIN_USERNAME = "admin",
            ADMIN_DEFAULT_PASSWORD = "12345678";

    private static Logger log = LoggerFactory.getLogger(AdminInitializer.class);

    private final UserService service;

    @Autowired
    public AdminInitializer(UserService service) {
        this.service = service;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        User superAdmin = UserFactory.createAdmin()
                .setDni("20334033D")
                .setName("Videoclub Admin")
                .setAddress("C/El Mejor Videoclub, nª1, bajo")
                .setPhone(693582471)
                .setEmail("admin@videoclub.com")
                .setUsername(ADMIN_USERNAME)
                .setPassword(ADMIN_DEFAULT_PASSWORD);

        Optional<User> possibleAdmin = service.findBy(UserQueryTypeSingle.USERNAME, ADMIN_USERNAME);
        if (!possibleAdmin.isPresent()) {
            log.info("No default admin was found, creating one");
            service.create(superAdmin);
        }

        Movie movie = new Movie()
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

        MovieService movieService = Services.getMovieService();
        movieService.create(movie);

        movie = new Movie()
                .setTitle("Prueba")
                .setTitleOv("Prueva_VO")
                .setYear(2010)
                .addActor(new Actor("Sr. X"))
                .addActor(new Actor("Batman"))
                .addDirector(new Director("Tipo de incógnito"))
                .addGenre(new Genre("Misterio"))
                .addGenre(new Genre("Comedia"))
                .setDescription("Reina en mi espíritu una alegría admirable, muy parecida a las dulces " +
                        "alboradas de la primavera, de que gozo aquí con delicia. Estoy solo, y me felicito " +
                        "de vivir en este país, el más a propósito para.")
                .setAvailableCopies(4);

        movieService.create(movie);
    }
}
