package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.helpers.PasswordEncryptor;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.repositories.UserRepository;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.services.utils.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * AuthenticationService with a DB source
 *
 * Uses {@link UserRepository} interface to access to the underlying DB
 *
 * Use @Autowire to create an instance from @Component or @Bean
 */
@Service
public class AuthenticationServiceDB implements AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncryptor encryptor;

    @Autowired
    public AuthenticationServiceDB(UserRepository repository, PasswordEncryptor encryptor) {
        this.repository = repository;
        this.encryptor = encryptor;
    }

    @Override
    public Result auth(String username, String password) {
        Optional<User> possibleUser = repository.findByUsername(username);
        Result error = ResultBuilder.error("LOGIN_FAILED");

        if (possibleUser.isPresent()) {
            User user = possibleUser.get();
            if (encryptor.equals(password, user.getPassword())) { //FIXME: Use BCrypt comparator
                return ResultBuilder.ok();
            }
            return error.addField("PASSWORD");
        }
        return error.addField("USERNAME");
    }
}
