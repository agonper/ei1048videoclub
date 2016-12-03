package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.utils.Result;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * User service interface that acts as a gateway between the user and the business logic.
 *
 * Every user service must implement this interface.
 */
public interface UserService {
    /**
     * Creates a user if it meets business logic constraints. If not returns an ERROR {@link Result} telling what went wrong.
     * @param user The {@link User} to be created.
     * @return An OK {@link Result}, if everything went fine. If not an ERROR with a message and the fields that do not meet the requisites.
     */
    Result create(User user);

    /**
     * Looks for a user by a determinate field
     * @param field search by one of {@link UserQueryTypeSingle}.
     * @param value attribute that must be matched.
     * @return A filled {@link Optional} if a user was found or an empty one if not.
     */
    Optional<User> findBy(UserQueryTypeSingle field, String value);

    /**
     * Looks for multiple users given a determinate field
     * @param field search by one of {@link UserQueryTypeMultiple}.
     * @param value attribute that must be matched.
     * @return A {@link Stream} with one or more records.
     */
    Stream<User> findAllBy(UserQueryTypeMultiple field, String value);

    /**
     * Updates the given user entity, it must be a non-new entity
     * @param user A non-new user entity
     * @return An OK {@link Result}, if everything went fine. If not an ERROR with a message and the fields that do not meet the requisites.
     */
    Result update(User user);

    /**
     * Deletes the given user entity, it must be a non-new entity
     * @param user A non-new user entity
     * @return An OK {@link Result}, if everything went fine. If not an ERROR.
     */
    Result delete(User user);
}
