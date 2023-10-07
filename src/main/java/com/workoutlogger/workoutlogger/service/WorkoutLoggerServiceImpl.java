package com.workoutlogger.workoutlogger.service;

import com.workoutlogger.workoutlogger.DAO.ExerciseDAO;
import com.workoutlogger.workoutlogger.DAO.UserDAO;
import com.workoutlogger.workoutlogger.DAO.WorkoutLogDAO;
import com.workoutlogger.workoutlogger.DTO.WorkoutLogDTO;
import com.workoutlogger.workoutlogger.entities.Exercise;
import com.workoutlogger.workoutlogger.entities.User;
import com.workoutlogger.workoutlogger.entities.WorkoutLog;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutLoggerServiceImpl implements WorkoutLoggerService{
    private UserDAO userDAO;
    private WorkoutLogDAO workoutLogDAO;
    private ExerciseDAO exerciseDAO;

    @Autowired
    public WorkoutLoggerServiceImpl(UserDAO userDAO, WorkoutLogDAO workoutLogDAO, ExerciseDAO exerciseDAO) {
        this.userDAO = userDAO;
        this.workoutLogDAO = workoutLogDAO;
        this.exerciseDAO = exerciseDAO;
    }

    @Override
    public void logExercise(WorkoutLogDTO workoutLogDTO) {
    }

    @Override
    @Transactional
    public void createExercise(Exercise exercise) {
        exerciseDAO.create(exercise);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        userDAO.create(user);
    }

    @Override
    @Transactional
    public WorkoutLogDTO getWorkoutLog(int id) {
        WorkoutLog workoutLog= workoutLogDAO.read(id);
        int exerciseId = workoutLog.getExerciseId();
        int userId = workoutLog.getUserId();
        Exercise exercise = exerciseDAO.read(exerciseId);
        User user = userDAO.read(userId);
        WorkoutLogDTO workoutLogDTO = new WorkoutLogDTO(workoutLog.getId(), exercise.getExerciseName(), exercise.getExerciseDescription(),
                user.getUsername(), workoutLog.getSets(), workoutLog.getReps(), workoutLog.getWeight());
        return workoutLogDTO;
    }

    @Override
    public Exercise getExercise(int id) {
        return null;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return null;
    }

    @Override
    public List<WorkoutLog> gettAllWorkoutLogsByUser(User user) {
        return null;
    }
}
