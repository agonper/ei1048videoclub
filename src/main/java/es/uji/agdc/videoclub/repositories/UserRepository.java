package es.uji.agdc.videoclub.repositories;

import es.uji.agdc.videoclub.models.User;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Repository (DAO) that permits CRUD operations on user entities
 */
public interface UserRepository extends CrudRepositoryJ8<User, Long> {

    /**
     * Search for a user by a given dni. If no user is found and empty {@link Optional} is returned.
     * @param dni DNI of the user to be found
     * @return Optional with the user if found
     */
    Optional<User> findByDni(String dni);

    /**
     * Search for a user by a given username. If no user is found and empty {@link Optional} is returned.
     * @param username Username of the user to be found
     * @return Optional with the user if found
     */
    Optional<User> findByUsername(String username);

    /**
     * Search for a user by a given email. If no user is found and empty {@link Optional} is returned.
     * @param email Email of the user to be found
     * @return Optional with the user if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Search for a users by a given role. If no user is found and empty {@link Stream} is returned.
     * @param role One of the types declared at {@link User.Role}
     * @return An stream with the users that have the given role
     */
    Stream<User> findByRole(User.Role role);
}
