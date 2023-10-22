package com.workoutlogger.workoutlogger.rest;

import com.workoutlogger.workoutlogger.DTO.WorkoutLogDTO;
import com.workoutlogger.workoutlogger.entities.Exercise;
import com.workoutlogger.workoutlogger.entities.User;
import com.workoutlogger.workoutlogger.service.WorkoutLoggerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private WorkoutLoggerService workoutLoggerService;
    public WorkoutController(WorkoutLoggerService workoutLoggerService) {
        this.workoutLoggerService = workoutLoggerService;
    }
    //******************************************************************************************************************
    @GetMapping("/exercises")
    public List<Exercise> exercisesGetMapping(){
        return workoutLoggerService.getAllExercises();
    }
    //******************************************************************************************************************
    @GetMapping("/exercises/{id}")
    Exercise exercisesGetMappingById(@PathVariable("id") int id) {
        return workoutLoggerService.getExercise(id);
    }
    //******************************************************************************************************************
    @PostMapping("/exercises")
    public void exercisesPostMapping(@RequestBody Exercise exercise) {
        workoutLoggerService.createExercise(exercise);
    }
    //******************************************************************************************************************
    @PutMapping("/exercises")
    public void exercisePutMapping(@RequestBody Exercise exercise) {
        workoutLoggerService.updateExercise(exercise);
    }
    //******************************************************************************************************************
    @DeleteMapping("/exercises/{id}")
    public void exerciseDeleteMapping(@PathVariable("id") int id) {
        workoutLoggerService.deleteExercise(id);
    }
    //******************************************************************************************************************
    @GetMapping("/users")
    public List<User> usersGetMapping(){
        return workoutLoggerService.getAllUsers();
    }
    //******************************************************************************************************************
    @GetMapping("/users/{id}")
    User usersGetMappingById(@PathVariable("id") int id) {
        return workoutLoggerService.getUserById(id);
    }
    //******************************************************************************************************************
    @PostMapping("/users")
    public void usersPostMapping(@RequestBody User user) {
        workoutLoggerService.createUser(user);
    }
    //******************************************************************************************************************
    @PutMapping("/users")
    public void usersPutMapping(@RequestBody User user) {
        workoutLoggerService.updateUser(user);
    }
    //******************************************************************************************************************
    @DeleteMapping("/users/{id}")
    public void usersDeleteMapping(@PathVariable("id") int id) {
        workoutLoggerService.deleteUser(id);
    }
    //******************************************************************************************************************

    @GetMapping("/test/{id}")
    public WorkoutLogDTO testMapping(@PathVariable("id") int id){
        return workoutLoggerService.getWorkoutLog(id);
    }
    @PostMapping("/test")
    public void testPutMapping(@RequestBody WorkoutLogDTO workoutLogDTO) {
        workoutLoggerService.logExercise(workoutLogDTO);
    }
}
