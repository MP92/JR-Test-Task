package mypackage.dao;

import mypackage.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserUserDaoImpl extends AbstractUserDao<Integer, User> implements UserDao {

    public User findUserById(int id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<User> findUsersByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.like("name", name+"%"));
        return (List<User>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }

    public void saveUser(User user) {
        persist(user);
    }

    public void deleteUserById(int id) {
        Query query = getSession().createSQLQuery("delete from User where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }
}
