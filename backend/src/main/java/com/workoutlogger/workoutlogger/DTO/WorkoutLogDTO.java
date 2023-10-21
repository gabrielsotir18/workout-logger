package com.workoutlogger.workoutlogger.DTO;

import jakarta.persistence.Entity;

import java.util.Date;

public class WorkoutLogDTO {
    private int id;
    private int exerciseId;
    private String exercise;
    private String exerciseDescription;
    private int userId;
    private String username;
    private int sets;
    private int reps;
    private int weight;
    private Date date;

    public WorkoutLogDTO() {
    }

    public WorkoutLogDTO(int id, int exerciseId, String exercise, String exerciseDescription, int userId, String username,
                         int sets, int reps, int weight, Date date) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.exercise = exercise;
        this.exerciseDescription = exerciseDescription;
        this.userId = userId;
        this.username = username;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
