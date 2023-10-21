package com.workoutlogger.workoutlogger.DAO;

import com.workoutlogger.workoutlogger.entities.Exercise;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class ExerciseDAOImpl implements ExerciseDAO{
    private final EntityManager entityManager;

    @Autowired
    ExerciseDAOImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Exercise exercise) {
        try{
            entityManager.persist(exercise);
        }
        catch(EntityExistsException e) {
            System.out.println("Entity exists");
        }

    }

    @Override
    public Exercise getById(int id) {
        return entityManager.find(Exercise.class, id);
    }

    @Override
    public void update(Exercise exercise) {
        entityManager.merge(exercise);
    }
    @Override
    public List<Exercise> getAllEntities() {
        System.out.println("ExerciseDAOImpl::getAllEntities");
        TypedQuery<Exercise> typedQuery = entityManager.createQuery("SELECT e FROM Exercise e", Exercise.class);
        return typedQuery.getResultList();
    }
    @Override
    public void delete(int id) {
        Exercise exercise = this.getById(id);
        entityManager.remove(exercise);
    }
}
