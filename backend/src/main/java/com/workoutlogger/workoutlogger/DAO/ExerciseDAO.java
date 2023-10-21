package com.workoutlogger.workoutlogger.DAO;

import com.workoutlogger.workoutlogger.entities.Exercise;

import java.util.List;

public interface ExerciseDAO {
    void create(Exercise exercise);
    List<Exercise> getAllEntities();
    Exercise getById(int id);
    void update(Exercise exercise);
    void delete(int id);

}
