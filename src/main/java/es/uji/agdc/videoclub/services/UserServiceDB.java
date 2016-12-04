package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.helpers.PasswordEncryptor;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.services.utils.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Alberto on 04/12/2016.
 */
@Service
public class UserServiceDB implements UserService{

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
        switch (field) {
            case USERNAME:
                return repository.findByUsername(value);
            default:
                // FIXME Implement remaining enum types
                throw new Error("Unimplemented");
        }
    }

    @Override
    public Stream<User> findAllBy(UserQueryTypeMultiple field, String value) {
        // FIXME
        throw new Error("Unimplemented");
    }

    @Override
    public Result update(User user) {
        // FIXME
        throw new Error("Unimplemented");
    }

    @Override
    public Result delete(User user) {
        // FIXME
        throw new Error("Unimplemented");
    }
}
