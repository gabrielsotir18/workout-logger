package com.workoutlogger.workoutlogger.DAO;

import com.workoutlogger.workoutlogger.entities.Exercise;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseDAOImpl implements ExerciseDAO{
    private final EntityManager entityManager;

    @Autowired
    ExerciseDAOImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Exercise exercise) {
        entityManager.persist(exercise);
    }

    @Override
    public Exercise read(int id) {
        return entityManager.find(Exercise.class, id);
    }

    @Override
    public void update(Exercise exercise) {
        entityManager.merge(exercise);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(id);
    }
}
