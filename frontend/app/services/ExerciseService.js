import ExerciseDefService from './ExerciseDefService.js'
import SetService from './SetService.js'

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
    {
      id: 5,
      exerciseDefId: 1,
      workoutId: 1
    },
    {
      id: 6,
      exerciseDefId: 2,
      workoutId: 1
    },
    {
      id: 7,
      exerciseDefId: 1,
      workoutId: 1
    },
    {
      id: 8,
      exerciseDefId: 2,
      workoutId: 1
    },
    {
      id: 9,
      exerciseDefId: 1,
      workoutId: 1
    },
    {
      id: 10,
      exerciseDefId: 2,
      workoutId: 1
    },
    {
      id: 11,
      exerciseDefId: 1,
      workoutId: 1
    },
    {
      id: 12,
      exerciseDefId: 2,
      workoutId: 1
    },
    {
      id: 13,
      exerciseDefId: 1,
      workoutId: 4,
    },
  ],
  getExercisesByWorkout(workoutId) {
    return this.exercises
      .filter((exercise) => exercise.workoutId === workoutId)
      .map((exercise) => this.getViewableExercise(exercise))
      .filter((exercise) => SetService.hasSets(exercise))
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
  },
  getExerciseDef(exerciseId) {
    return this.exercises.find(
      (exercise) => exercise.id === exerciseId
    ).exerciseDefId
  },
  addExercise(workout, exerciseDef) {
    const maxId = Math.max(...this.exercises.map(o => o.id))
    const newExercise = {
      id: maxId + 1,
      exerciseDefId: exerciseDef.id,
      workoutId: workout.id
    }
    this.exercises.push(newExercise)
    return newExercise
  },
  getExercise(workout, exerciseDef) {
    const filtered = this.exercises.find(
      // eslint-disable-next-line max-len
      (exercise) => exercise.workoutId === workout.id && exercise.exerciseDefId === exerciseDef.id
    )
    return filtered || this.addExercise(workout, exerciseDef)
  },
  getExerciseById(id) {
    return this.exercises.find((exercise) => exercise.id === id)
  }
}