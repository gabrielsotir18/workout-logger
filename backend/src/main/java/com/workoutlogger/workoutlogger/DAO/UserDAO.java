package com.workoutlogger.workoutlogger.DAO;

import com.workoutlogger.workoutlogger.entities.User;

import java.util.List;

public interface UserDAO {
    void create(User user);
    User getById(int id);
    List<User> getAllEntities();
    User getByUsername(String username);
    void update(User user);
    void delete(int id);
}
