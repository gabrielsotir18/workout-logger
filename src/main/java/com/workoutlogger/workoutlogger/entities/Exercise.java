package com.workoutlogger.workoutlogger.entities;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private int id;

    @Column(name = "name")
    private String exerciseName;

    @Column(name="description")
    private String exerciseDescription;

    public Exercise() {
    }

    public Exercise(int id, String exerciseName, String exerciseDescription) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", exerciseName='" + exerciseName + '\'' +
                ", exerciseDescription='" + exerciseDescription + '\'' +
                '}';
    }
}
