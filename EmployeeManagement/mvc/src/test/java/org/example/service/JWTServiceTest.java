package org.example.service;

import io.jsonwebtoken.Claims;
import org.example.ApplicationBootstrap;
import org.example.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mockStatic;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class JWTServiceTest {
    @Autowired
    private JWTService jwtService;

    @Test
    public void generateTokenTest() {
        User u = new User();
        u.setId(1);
        u.setName("Quan");

        String token = jwtService.generateToken(u);

        String[] array = token.split("\\.");
        boolean bool = array.length == 3 ? true: false;
        assertTrue(bool);
    }

    @Test
    public void decodeTokenTest() {
        User u = new User();
        u.setId(1);
        u.setName("Quan");

        String token = jwtService.generateToken(u);

        Claims claims = jwtService.decodeToken(token);
        String userName = claims.getSubject();
        assertEquals(u.getName(), userName);
    }
}
