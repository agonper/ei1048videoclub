package es.uji.agdc.videoclub.initializers;

import es.uji.agdc.videoclub.helpers.Services;
import es.uji.agdc.videoclub.models.*;
import es.uji.agdc.videoclub.models.utils.UserFactory;
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

        superAdmin = UserFactory.createMember()
                .setDni("20332033D")
                .setName("Videoclub")
                .setAddress("C/El Mejor Videoclub, nª2")
                .setPhone(693582441)
                .setEmail("admin@videoclub.es")
                .setUsername("prueba")
                .setPassword("pruebaprueba");

        service.create(superAdmin);

        Movie movie = new Movie()
                .setTitle("Capitán América")
                .setTitleOv("Captain America")
                .setYear(2011)
                .addActor(new Actor("Chris Evans"))
                .addActor(new Actor("Hayley Atwell"))
                .addDirector(new Director("Joe Johnston"))
                .addGenre(new Genre("Comedia"))
                .setDescription("Y, viéndole don Quijote de aquella manera, con muestras de tanta " +
                        "tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no " +
                        "hace más que otro. Todas estas borrascas que nos suceden son.")
                .setAvailableCopies(3);

        Services.getMovieService().create(movie);
    }
}
