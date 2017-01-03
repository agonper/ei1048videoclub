package es.uji.agdc.videoclub.validators;

import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.services.utils.ResultBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created by daniel on 3/01/17.
 */

@Component
public class UserValidator implements Validator<User> {

    @Override
    public Result validate(User entity) {
        Result emptyFieldsResult = checkIfHasEmptyFields(entity);

        if (emptyFieldsResult.isError())
            return emptyFieldsResult;

        Result invalidFieldsResult = checkIfHasInvalidFields(entity);
        if (invalidFieldsResult.isError())
            return invalidFieldsResult;

        return ResultBuilder.ok();
    }

    private Result checkIfHasEmptyFields(User entity) {
        Result emptyError = ResultBuilder.error("EMPTY_PARAMETER");

        if (fieldEmpty(entity.getDni()))
            emptyError.addField("DNI");

        if (fieldEmpty(entity.getName()))
            emptyError.addField("Name");

        if (fieldEmpty(entity.getAddress()))
            emptyError.addField("Address");

        if (fieldEmpty(Integer.toString(entity.getPhone())))
            emptyError.addField("Phone");

        if (fieldEmpty(entity.getEmail()))
            emptyError.addField("Email");

        if (entity.getLastPayment() == null || fieldEmpty(entity.getLastPayment().toString()) && !entity.isAdmin())
            emptyError.addField("LastPayment");

        if (fieldEmpty(entity.getUsername()))
            emptyError.addField("Username");

        if (fieldEmpty(entity.getPassword()))
            emptyError.addField("Password");

        if (emptyError.getFields().length > 0)
            return emptyError;

        return ResultBuilder.ok();
    }

    private Result checkIfHasInvalidFields(User entity) {
        Result invalidField = ResultBuilder.error("INVALID_PARAMETER");

        boolean dni_incorrect_length = entity.getDni().length() != 9;
        char dniLetter = entity.getDni().charAt(8);
        boolean dni_invalid_letter = !(dniLetter >= 65 || dniLetter <= 90) || !(dniLetter >= 97 || dniLetter <= 122);
        boolean dni_invalid_number = !isNumber(entity.getDni().substring(0, 8));
        boolean dni_invalid_format = dni_invalid_letter || dni_invalid_number;

        if (dni_incorrect_length || dni_invalid_format)
            invalidField.addField("DNI");

        if (entity.getName().length() < 2)
            invalidField.addField("Name");

        //FIXME: Encontrar un buen método para asegurar esto
        if (entity.getAddress().length() < 5)
            invalidField.addField("Address");

        if (entity.getPhone() < 111111111 || entity.getPhone() > 999999999)
            invalidField.addField("Phone");

        if (!isEmail(entity.getEmail()))
            invalidField.addField("Email");

        int actualYear = LocalDate.now().getYear();
        if (entity.getLastPayment().getYear() > 1950 && entity.getLastPayment().getYear() < actualYear)
            invalidField.addField("LastPayment");

        if (entity.getUsername().length() < 4 && entity.getUsername().length() > 20)
            invalidField.addField("Username");

        if (entity.getPassword().length() < 4 && entity.getPassword().length() > 20)
            invalidField.addField("Password");

        if (invalidField.getFields().length > 0)
            return invalidField;

        return ResultBuilder.ok();
    }

    private boolean fieldEmpty(String field) {
        return (field == null || field.trim().length() == 0);
    }

    private boolean isNumber(String string) {
        char[] chars = string.toCharArray();
        boolean isNumber = true;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < 48 || chars[i] > 57) {
                isNumber = false;
                break;
            }
        }

        return isNumber;
    }

    private boolean isEmail(String email) {
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern p = Pattern.compile(EMAIL_PATTERN);

        return p.matcher(email).matches();
    }
}
