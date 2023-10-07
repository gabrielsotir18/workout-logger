package com.workoutlogger.workoutlogger.DTO;

import jakarta.persistence.Entity;

public class WorkoutLogDTO {
    private int id;
    private String exercise;
    private String exerciseDescription;
    private String user;
    private int sets;
    private int reps;
    private int weight;

    public WorkoutLogDTO() {
    }

    public WorkoutLogDTO(int id, String exercise, String exerciseDescription, String user, int sets, int reps, int weight) {
        this.id = id;
        this.exercise = exercise;
        this.exerciseDescription = exerciseDescription;
        this.user = user;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
