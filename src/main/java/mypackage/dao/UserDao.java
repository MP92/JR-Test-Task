package mypackage.dao;

import mypackage.model.User;

import java.util.List;

public interface UserDao {
    User findUserById(int id);

    List<User> findUsersByName(String name);

    List<User> findAllUsers();

    void saveUser(User user);

    void deleteUserById(int id);
}
