package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.services.utils.Result;

/**
 * Authentication service interface every authentication service should implement this interface
 */
public interface AuthenticationService {
    /**
     * Tries to authenticate a user based on a given username and password
     * @param username
     * @param password
     * @return An ok result if everything went fine,
     * or an error with the field that was not corresponding
     * with user data.
     */
    Result auth(String username, String password);
}
