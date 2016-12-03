package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.services.utils.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Alberto on 03/12/2016.
 */
@Service
public class AuthenticationServiceDB implements AuthenticationService {

    private final UserRepository repository;

    @Autowired
    public AuthenticationServiceDB(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result auth(String username, String password) {
        Optional<User> possibleUser = repository.findByUsername(username);
        Result error = ResultBuilder.error("LOGIN_FAILED");

        if (possibleUser.isPresent()) {
            User user = possibleUser.get();
            if (user.getPassword().equals(password)) { //FIXME: Use BCrypt comparator
                return ResultBuilder.ok();
            }
            return error.addField("PASSWORD");
        }
        return error.addField("USERNAME");
    }
}
