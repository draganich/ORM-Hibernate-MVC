package web.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> userList(int num) {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void save(User user) { entityManager.persist(user); }

    @Override
    public User getID(int id) { return entityManager.find(User.class, id); }

    @Override
    public void update(int id, User updatedUser) { entityManager.merge(updatedUser); }

    @Override
    public void delete(int id) { entityManager.remove(entityManager.find(User.class, id)); }
}