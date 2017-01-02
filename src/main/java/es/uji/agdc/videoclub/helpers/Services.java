package es.uji.agdc.videoclub.helpers;

import es.uji.agdc.videoclub.services.AuthenticationService;
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

    @Autowired
    public Services(UserService userService, AuthenticationService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    public static UserService getUserService() {
        return Services.userService;
    }

    public static AuthenticationService getAuthenticationService() {
        return Services.authService;
    }
}
