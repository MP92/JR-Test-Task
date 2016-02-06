package mypackage.service;

import mypackage.model.User;

import java.util.List;

public interface UserService {
    User findUserById(int id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(int id);

    List<User> findAllUsers();

    List<User> findUsersByName(String name);
}
