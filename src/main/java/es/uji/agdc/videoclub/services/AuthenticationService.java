package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.services.utils.Result;

/**
 * Created by Alberto on 03/12/2016.
 */
public interface AuthenticationService {
    Result auth(String username, String password);
}
