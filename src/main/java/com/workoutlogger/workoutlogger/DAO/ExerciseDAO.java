package com.workoutlogger.workoutlogger.DAO;

import com.workoutlogger.workoutlogger.entities.Exercise;

public interface ExerciseDAO {
    void create(Exercise exercise);
    Exercise read(int id);
    void update(Exercise exercise);
    void delete(int id);

}
