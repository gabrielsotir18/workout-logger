package com.workoutlogger.workoutlogger.entities;

import com.workoutlogger.workoutlogger.DTO.WorkoutLogDTO;
import jakarta.persistence.*;
import org.hibernate.jdbc.Work;

import java.util.Date;

@Entity
@Table(name = "workoutlogs")
public class WorkoutLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "exercise_id")
    private int exerciseId;

    @Column(name = "sets")
    private int sets;

    @Column(name = "reps")
    private int reps;

    @Column(name = "weight")
    private int weight;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public WorkoutLog(){

    }

    public WorkoutLog(int id, int userId, int exerciseId, int sets, int reps, int weight, Date date) {
        this.id = id;
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WorkoutLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", sets=" + sets +
                ", reps=" + reps +
                ", weight=" + weight +
                ", date='" + date + '\'' +
                '}';
    }

    public void setExerciseData(WorkoutLogDTO workoutLogDTO) {
        this.setExerciseId(workoutLogDTO.getExerciseId());
        this.setReps(workoutLogDTO.getReps());
        this.setSets(workoutLogDTO.getSets());
        this.setWeight(workoutLogDTO.getWeight());
        this.setDate(workoutLogDTO.getDate());

    }
}
