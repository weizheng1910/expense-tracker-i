package com.example.expensetrackeri.service;

import com.example.expensetrackeri.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
