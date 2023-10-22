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
    @Transactional
    public void logExercise(WorkoutLogDTO workoutLogDTO) {
        User user = userDAO.getById(workoutLogDTO.getUserId());

        WorkoutLog workoutLog = new WorkoutLog();
        workoutLog.setUserId(user.getId());
        workoutLog.setExerciseData(workoutLogDTO);
        workoutLogDAO.create(workoutLog);
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
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDAO.getById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllEntities();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    @Override
    @Transactional
    public WorkoutLogDTO getWorkoutLog(int id) {
        WorkoutLog workoutLog= workoutLogDAO.read(id);
        int exerciseId = workoutLog.getExerciseId();
        int userId = workoutLog.getUserId();
        Exercise exercise = exerciseDAO.getById(exerciseId);
        User user = userDAO.getById(userId);
        return new WorkoutLogDTO(workoutLog.getId(),
                exercise.getId() ,exercise.getExerciseName(), exercise.getExerciseDescription(),
                user.getId(), user.getUsername(),
                workoutLog.getSets(), workoutLog.getReps(), workoutLog.getWeight(), workoutLog.getDate());
    }

    @Override
    @Transactional
    public Exercise getExercise(int id) {
        return exerciseDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Exercise> getAllExercises() {
        return exerciseDAO.getAllEntities();
    }

    @Override
    @Transactional
    public void deleteExercise(int id) {
        exerciseDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateExercise(Exercise exercise) {
        exerciseDAO.update(exercise);
    }
    @Override
    @Transactional
    public List<WorkoutLog> getAllWorkoutLogsByUser(User user) {
        return null;
    }
}
