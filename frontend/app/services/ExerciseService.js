import ExerciseDefService from './ExerciseDefService.js'

export default {
  exercises: [
    {
      id: 1,
      exerciseDefId: 1,
      workoutId: 1
    },
    {
      id: 2,
      exerciseDefId: 2,
      workoutId: 1
    },
    {
      id: 3,
      exerciseDefId: 3,
      workoutId: 2
    },
    {
      id: 4,
      exerciseDefId: 1,
      workoutId: 3
    },
  ],
  getExercisesByWorkout(workoutId) {
    return this.exercises
      .filter((exercise) => exercise.workoutId === workoutId)
      .map((exercise) => this.getViewableExercise(exercise))
  },
  getViewableExercise(exercise) {
    if (!exercise) {
      return {}
    }
    const exerciseDef = ExerciseDefService.getExerciseDefById(
      exercise.exerciseDefId
    )
    return {
      id: exercise.id,
      name: exerciseDef.name,
      muscleGroup: exerciseDef.muscleGroup
    }
  }
}