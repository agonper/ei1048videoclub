package unit.es.uji.agdc.videoclub.validators;

import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.validators.UserValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alberto on 15/12/16.
 */
public class UserValidatorTest {

    private UserValidator validator;
    private User user;

    @Before
    public void setUp() throws Exception {
        validator = new UserValidator();
        user = new User() //FIXME Create a fixture factory
                .setDni("10614397N")
                .setName("Paco Sánchez Díaz")
                .setAddress("C/Falsa, 123, 1º")
                .setPhone(693582471)
                .setEmail("pacosd@hotmail.com")
                .setUsername("paquito69")
                .setPassword("pacosd69")
                .setRole(User.Role.MEMBER);
    }

    @Test
    public void validate_valid_isValid() throws Exception {
        Result result = validator.validate(user);
        assertTrue(result.isOk());
    }

    @Test
    public void validate_emptyDni_isInvalid() throws Exception {
        user.setDni("");
        Result result = validator.validate(user);
        assertTrue(result.isError());
        assertEquals("Result: ERROR(EMPTY_PARAMETER)[Dni]", result.toString());
    }

    @Test
    public void validate_nullDni_isInvalid() throws Exception {
        user.setDni(null);
        Result result = validator.validate(user);
        assertTrue(result.isError());
        assertEquals("Result: ERROR(EMPTY_PARAMETER)[Dni]", result.toString());
    }

    @After
    public void tearDown() throws Exception {
        validator = null;
        user = null;
    }

}