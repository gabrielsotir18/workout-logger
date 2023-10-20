package com.workoutlogger.workoutlogger.service;

import com.workoutlogger.workoutlogger.DTO.WorkoutLogDTO;
import com.workoutlogger.workoutlogger.entities.Exercise;
import com.workoutlogger.workoutlogger.entities.User;
import com.workoutlogger.workoutlogger.entities.WorkoutLog;

import java.util.List;

public interface WorkoutLoggerService {
     void logExercise(WorkoutLogDTO workoutLogDTO);
     void createExercise(Exercise exercise);
     void createUser(User user);
     WorkoutLogDTO getWorkoutLog(int id);
     Exercise getExercise(int id);
     List<Exercise> getAllExercises();
     List<WorkoutLog> getAllWorkoutLogsByUser(User user);


}
