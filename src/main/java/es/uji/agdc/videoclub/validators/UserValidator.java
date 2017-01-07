package es.uji.agdc.videoclub.validators;

import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.utils.Result;
import org.springframework.stereotype.Component;

/**
 * Created by alberto on 15/12/16.
 */
@Component
public class UserValidator extends Validator<User> {

    @Override
    protected Result checkIfHasEmptyFields(User entity, Result emptyError) {
        if (isFieldEmpty(entity.getDni())) {
            emptyError.addField("Dni");
        }
        return emptyError;
    }

    @Override
    protected Result checkIfHasInvalidFields(User entity, Result invalidError) {


        return invalidError;
    }
}
