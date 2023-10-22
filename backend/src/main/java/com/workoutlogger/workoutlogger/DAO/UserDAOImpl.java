package com.workoutlogger.workoutlogger.DAO;

import com.workoutlogger.workoutlogger.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.TypeElement;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByUsername(String username) {
        System.out.println("UserDAOImpl::getByUsername");
        System.out.println("UserDAOImpl::getByUsername - username[" + username + "]");

        TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username",
                User.class);
        typedQuery.setParameter("username", username);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<User> getAllEntities() {
        System.out.println("UserDAOImpl::getAllEntities");
        TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
        return typedQuery.getResultList();
    }
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        User user = this.getById(id);
        entityManager.remove(user);
    }
}
