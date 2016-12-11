package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.helpers.PasswordEncryptor;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.services.utils.ResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Alberto on 04/12/2016.
 */
@Service
public class UserServiceDB implements UserService{
    Logger log = LoggerFactory.getLogger(UserServiceDB.class);

    private final UserRepository repository;
    private final PasswordEncryptor encryptor;

    @Autowired
    public UserServiceDB(UserRepository repository, PasswordEncryptor encryptor) {
        this.repository = repository;
        this.encryptor = encryptor;
    }

    @Override
    public Result create(User user) {

        if (!user.isNew()) {
            log.warn("create(): called with a non-new user");
            return ResultBuilder.error("OLD_USER");
        }

        try {
            // FIXME Control all different edge cases

            user.setPassword(encryptor.hash(user.getPassword()));
            repository.save(user);
            return ResultBuilder.ok();
        } catch (Exception e) {
            return ResultBuilder.error(e.getMessage());
        }
    }

    @Override
    public Optional<User> findBy(UserQueryTypeSingle field, String value) {
        if (isNonValidValue(value)) {
            log.warn("findBy(" + field + "): Called with null or empty value");
            return Optional.empty();
        }

        switch (field) {
            case ID:
                return findOneIfValidId(value);
            case DNI:
                return repository.findByDni(value);
            case USERNAME:
                return repository.findByUsername(value);
            case EMAIL:
                return repository.findByEmail(value);
            default:
                throw new Error("Unimplemented");
        }
    }

    private boolean isNonValidValue(String value) {
        return value == null || value.isEmpty();
    }

    private Optional<User> findOneIfValidId(String value) {
        try {
            return repository.findOne(Long.valueOf(value));
        } catch (NumberFormatException e) {
            log.warn("ID couldn't be parsed. " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Stream<User> findAllBy(UserQueryTypeMultiple field, String value) {
        if (isNonValidValue(value)) {
            log.warn("findAllBy(" + field + "): Called with null or empty value");
            return Stream.empty();
        }

        switch (field) {
            case ROLE:
                return findAllIfValidRole(value);
            default:
                throw new Error("Unimplemented");
        }
    }

    private Stream<User> findAllIfValidRole(String value) {
        try {
            return repository.findByRole(User.Role.valueOf(value.toUpperCase()));
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            return Stream.empty();
        }
    }

    @Override
    public Result update(User user) {
        // FIXME Implement
        throw new Error("Unimplemented");
    }

    @Override
    public Result remove(String username) {
        // FIXME Implement
        throw new Error("Unimplemented");
    }
}
