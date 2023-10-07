package com.workoutlogger.workoutlogger.DAO;

import com.workoutlogger.workoutlogger.entities.WorkoutLog;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkoutLogDAOImpl implements WorkoutLogDAO{
    private EntityManager entityManager;

    @Autowired
    public WorkoutLogDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void create(WorkoutLog workoutLog) {
        entityManager.persist(workoutLog);
    }

    @Override
    public WorkoutLog read(int id) {
        return entityManager.find(WorkoutLog.class, id);
    }

    @Override
    public void update(WorkoutLog workoutLog) {
        entityManager.merge(workoutLog);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(id);
    }
}
