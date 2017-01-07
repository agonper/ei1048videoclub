package es.uji.agdc.videoclub.validators;

import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.utils.Result;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created by daniel on 15/12/16.
 */
@Component
public class UserValidator extends Validator<User> {

    @Override
    protected Result checkIfHasEmptyFields(User entity, Result emptyError) {

        if (isFieldEmpty(entity.getDni()))
            emptyError.addField("DNI");

        if (isFieldEmpty(entity.getName()))
            emptyError.addField("Name");

        if (isFieldEmpty(entity.getAddress()))
            emptyError.addField("Address");

        if (isFieldEmpty(Integer.toString(entity.getPhone())))
            emptyError.addField("Phone");

        if (isFieldEmpty(entity.getEmail()))
            emptyError.addField("Email");

        if (isFieldEmpty(entity.getUsername()))
            emptyError.addField("Username");

        if (isFieldEmpty(entity.getPassword()))
            emptyError.addField("Password");

        return emptyError;
    }

    @Override
    protected Result checkIfHasInvalidFields(User entity, Result invalidError) {

        boolean dni_incorrect_length = entity.getDni().length() != 9;
        char dniLetter = entity.getDni().charAt(8);
        boolean dni_invalid_letter = !(dniLetter >= 65 || dniLetter <= 90) || !(dniLetter >= 97 || dniLetter <= 122);
        boolean dni_invalid_number = !isNumber(entity.getDni().substring(0, 8));
        boolean dni_invalid_format = dni_invalid_letter || dni_invalid_number;

        if (dni_incorrect_length || dni_invalid_format)
            invalidError.addField("DNI");

        if (entity.getName().length() < 2)
            invalidError.addField("Name");

        //FIXME: Encontrar un buen método para asegurar esto
        if (entity.getAddress().length() < 5)
            invalidError.addField("Address");

        if (entity.getPhone() < 111111111 || entity.getPhone() > 999999999)
            invalidError.addField("Phone");

        if (!isEmail(entity.getEmail()))
            invalidError.addField("Email");

        int actualYear = LocalDate.now().getYear();
        LocalDate lastPayment = entity.getLastPayment();
        if (lastPayment != null && lastPayment.getYear() > 1950 && lastPayment.getYear() < actualYear)
            invalidError.addField("LastPayment");

        if (entity.getUsername().length() < 4 && entity.getUsername().length() > 20)
            invalidError.addField("Username");

        if (entity.getPassword().length() < 4 && entity.getPassword().length() > 20)
            invalidError.addField("Password");


        return invalidError;
    }

    private boolean isEmail(String email) {
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern p = Pattern.compile(EMAIL_PATTERN);

        return p.matcher(email).matches();
    }
}
