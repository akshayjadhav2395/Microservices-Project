package com.aj.user.service.service;

import com.aj.user.service.entities.User;

import java.util.List;

public interface UserServiceI {

    public User createUser(User user);
    public List<User> getAllUsers();
    public User updateUser(User user, String userId);
    public User getSingleUser(String userId);
    public void deleteUser(String userId);
}
