package com.workoutlogger.workoutlogger.DAO;

import com.workoutlogger.workoutlogger.entities.User;

public interface UserDAO {
    void create(User user);
    User getById(int id);
    User getByUsername(String username);
    void update(User user);
    void delete(int id);
}
