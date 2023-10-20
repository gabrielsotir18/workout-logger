package com.workoutlogger.workoutlogger.rest;

import com.workoutlogger.workoutlogger.DTO.WorkoutLogDTO;
import com.workoutlogger.workoutlogger.service.WorkoutLoggerService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/test")
    public void testPutMapping(@RequestBody WorkoutLogDTO workoutLogDTO) {
        workoutLoggerService.logExercise(workoutLogDTO);
    }
}
