package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.User;

import java.util.Optional;

/**
 * Created by daniel on 4/01/17.
 */

//TODO: Finish service

public interface UserAssetService {

    Optional<User> findUserByDNI(String dni);

    Optional<User> findUserByUsername(String username);
}
