package com.workoutlogger.workoutlogger.rest;

import com.workoutlogger.workoutlogger.DTO.WorkoutLogDTO;
import com.workoutlogger.workoutlogger.service.WorkoutLoggerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private WorkoutLoggerService workoutLoggerService;
    public WorkoutController(WorkoutLoggerService workoutLoggerService) {
        this.workoutLoggerService = workoutLoggerService;
    }
    @GetMapping("/test/{id}")
    public WorkoutLogDTO testMapping(@PathVariable("id") int id){
        return workoutLoggerService.getWorkoutLog(id);
    }
}
