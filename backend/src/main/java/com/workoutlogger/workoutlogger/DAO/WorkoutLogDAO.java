package com.workoutlogger.workoutlogger.DAO;

import com.workoutlogger.workoutlogger.entities.WorkoutLog;

public interface WorkoutLogDAO {
    void create(WorkoutLog workoutLog);
    WorkoutLog read(int id);
    void update(WorkoutLog workoutLog);
    void delete(int id);
}
