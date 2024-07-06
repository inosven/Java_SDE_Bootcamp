package org.example.controller;

import org.example.model.User;
import org.example.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.example.service.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity userLogin(@RequestBody User user) {
        User validatedUser;
        try {
            validatedUser = userService.getUserByCredentials(user.getEmail(), user.getPassword());

        } catch (Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
//        return ResponseEntity.ok().body(jwtService.generateToken(validatedUser));

        if(validatedUser !=null)

    {
        return ResponseEntity.ok().body(jwtService.generateToken(validatedUser));
    }
            return ResponseEntity.badRequest().build();
}
}
