package es.uji.agdc.model.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by alberto on 1/12/16.
 */

@RunWith(Parameterized.class)
public class UserTest {
    private final String dni;
    private final String name;
    private final String address;
    private final int phone;
    private final String email;
    private final LocalDate birthday;
    private final String username;
    private final String password;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {
                    "10614397N",
                    "Paco Sánchez Díaz",
                    "C/Falsa, 123, 1º",
                    693582471,
                    "pacosd@hotmail.com",
                    LocalDate.of(2016, 10, 1),
                    "paquito69",
                    "pacosd69"
                }
        });
    }

    public UserTest(String dni, String name,
                    String address, int phone, String email, LocalDate birthday,
                    String username, String password) {

        this.dni = dni;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
    }

    @Test
    public void createUser() throws Exception {
        User user = new User()
                .setDni(dni)
                .setName(name)
                .setAddress(address)
                .setPhone(phone)
                .setEmail(email)
                .setBirthday(birthday)
                .setUsername(username)
                .setPassword(password);
        assertEquals(String.format("User{" +
                "dni='%s'," +
                " name='%s'," +
                " address='%s'," +
                " phone=%d," +
                " email='%s'," +
                " birthday=%s," +
                " username='%s'," +
                " password='%s'}",
                dni, name,
                address, phone, email, birthday.toString(),
                username, password), user.toString());
    }
}