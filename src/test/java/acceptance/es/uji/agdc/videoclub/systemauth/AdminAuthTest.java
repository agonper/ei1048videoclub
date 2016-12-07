package acceptance.es.uji.agdc.videoclub.systemauth;

import es.uji.agdc.videoclub.Main;
import es.uji.agdc.videoclub.initializers.AdminInitializer;
import es.uji.agdc.videoclub.services.AuthenticationService;
import es.uji.agdc.videoclub.services.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Alberto on 07/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class AdminAuthTest {
    private static int FIRST_FIELD = 0;

    @Autowired
    private AuthenticationService authService;

    @Test
    public void auth_correctAuthentication_returnsOk() throws Exception {
        // Given that we're creating a super admin during application initialization, we try to authenticate it
        Result authResult = authService.auth(AdminInitializer.ADMIN_USERNAME, AdminInitializer.ADMIN_DEFAULT_PASSWORD);

        // Assert that auth returns OK
        assertTrue(authResult.isOk());
    }

    @Test
    public void auth_incorrectAuthenticationWrongUsername_returnsLOGIN_FAILED_USERNAME() throws Exception {
        // Given that we're creating a super admin during application initialization, we fail to introduce his/her username
        Result authResult = authService.auth("administrator", "foo");

        // Assert that auth returns an error with message: LOGIN_FAILED and the field that fails is USERNAME
        assertTrue(authResult.isError());
        assertEquals("LOGIN_FAILED", authResult.getMsg());
        assertEquals("USERNAME", authResult.getFields()[FIRST_FIELD]);
    }


    @Test
    public void auth_incorrectAuthenticationWrongPassword_returnsLOGIN_FAILED_PASSWORD() throws Exception {
        // Given that we're creating a super admin during application initialization, we fail to introduce his/her username
        Result authResult = authService.auth(AdminInitializer.ADMIN_USERNAME, "foo");

        // Assert that auth returns an error with message: LOGIN_FAILED and the field that fails is PASSWORD
        assertTrue(authResult.isError());
        assertEquals("LOGIN_FAILED", authResult.getMsg());
        assertEquals("PASSWORD", authResult.getFields()[FIRST_FIELD]);
    }
}
