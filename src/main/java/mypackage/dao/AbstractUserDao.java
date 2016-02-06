package mypackage.dao;

import mypackage.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractUserDao<PK extends Serializable, T> implements UserDao {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractUserDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return getSession().get(persistentClass, key);
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

    public abstract User findUserById(int id);

    public abstract List<User> findUsersByName(String name);

    public abstract List<User> findAllUsers();

    public abstract void saveUser(User user);

    public abstract void deleteUserById(int id);
}
