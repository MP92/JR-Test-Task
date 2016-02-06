package mypackage.service;

import java.util.List;

import mypackage.dao.UserDao;
import mypackage.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    public User findUserById(int id) {
        return dao.findUserById(id);
    }

    public void saveUser(User user) {
        System.out.println(user.getCreatedDate());
        dao.saveUser(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
        User entity = dao.findUserById(user.getId());
        if (entity != null){
            entity.setName(user.getName());
            entity.setAge(user.getAge());
            entity.setAdmin(user.isAdmin());
            entity.setCreatedDate(user.getCreatedDate());
        }
    }

    public void deleteUserById(int id) {
        dao.deleteUserById(id);
    }

    public List<User> findUsersByName(String name) {
        return dao.findUsersByName(name);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }
}
