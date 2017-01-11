package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.helpers.PasswordEncryptor;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.services.utils.ResultBuilder;
import es.uji.agdc.videoclub.validators.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Alberto on 04/12/2016.
 */
@Service
public class UserServiceDB implements UserService{
    private Logger log = LoggerFactory.getLogger(UserServiceDB.class);
    private static final int UNPAID_MONTHS_TO_BECOME_A_DEFAULTER = 1;

    private final UserRepository repository;
    private final PasswordEncryptor encryptor;
    private final Validator<User> validator;

    @Autowired
    public UserServiceDB(UserRepository repository, PasswordEncryptor encryptor, Validator<User> validator) {
        this.repository = repository;
        this.encryptor = encryptor;
        this.validator = validator;
    }

    @Override
    public Result create(User user) {

        if (!user.isNew()) {
            log.warn("create(): called with a non-new user");
            return ResultBuilder.error("OLD_USER");
        }

        Result validatorResult = validator.validate(user);
        if (validatorResult.isError()) {
            return validatorResult;
        }

        Result alreadyExists = ResultBuilder.error("USER_ALREADY_EXISTS");

        Optional<User> userByDni = findBy(UserQueryTypeSingle.DNI, user.getDni());
        if (userByDni.isPresent()) {
            return alreadyExists.addField("DNI");
        }

        Optional<User> userByUsername = findBy(UserQueryTypeSingle.USERNAME, user.getUsername());
        if (userByUsername.isPresent()) {
            return alreadyExists.addField("Username");
        }

        Optional<User> userByEmail = findBy(UserQueryTypeSingle.EMAIL, user.getEmail());
        if (userByEmail.isPresent()) {
            return alreadyExists.addField("Email");
        }

        try {
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
    public Stream<User> findDefaulterUsers() {
        return repository.findByLastPaymentBefore(LocalDate.now().minusMonths(UNPAID_MONTHS_TO_BECOME_A_DEFAULTER));
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
