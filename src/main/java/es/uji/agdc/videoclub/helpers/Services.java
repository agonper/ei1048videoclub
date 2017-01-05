package es.uji.agdc.videoclub.helpers;

import es.uji.agdc.videoclub.services.AuthenticationService;
import es.uji.agdc.videoclub.services.MovieService;
import es.uji.agdc.videoclub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 15/12/16.
 */

@Component
public class Services {

    //TODO: Test
    private static UserService userService;
    private static AuthenticationService authService;
    private static MovieService movieService;

    @Autowired
    public Services(UserService userService, AuthenticationService authService, MovieService movieService) {
        this.userService = userService;
        this.authService = authService;
        this.movieService = movieService;
    }

    public static UserService getUserService() {
        return Services.userService;
    }

    public static AuthenticationService getAuthenticationService() {
        return Services.authService;
    }

    public static MovieService getMovieService() {
        return Services.movieService;
    }
}
