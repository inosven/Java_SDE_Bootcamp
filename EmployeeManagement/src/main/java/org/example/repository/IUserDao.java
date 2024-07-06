package org.example.repository;

import org.example.model.User;

public interface IUserDao {
    User getUserByCredentials(String email, String password);
}
