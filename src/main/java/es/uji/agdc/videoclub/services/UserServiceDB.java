package es.uji.agdc.videoclub.services;

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

    @Autowired
    public UserServiceDB(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result create(User user) {

        if (!user.isNew()) {
            return ResultBuilder.error("OLD_USER");
        }

        try {
            // FIXME Control all different edge cases

            // FIXME Before saving, hash user password
            repository.save(user);
            return ResultBuilder.ok();
        } catch (Exception e) {
            return ResultBuilder.error(e.getMessage());
        }
    }

    @Override
    public Optional<User> findBy(UserQueryTypeSingle field, String value) {
        // FIXME
        throw new Error("Unimplemented");
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
