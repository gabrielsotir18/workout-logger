export default {
  exerciseDef: [
    {
      id: 1,
      name: 'Barbell Bench Press',
      muscleGroup: 'Chest'
    },
    {
      id: 2,
      name: 'Dumbell Bench Press',
      muscleGroup: 'Chest'
    },
    {
      id: 3,
      name: 'Pull-Up',
      muscleGroup: 'Back'
    }
  ],
  getExerciseDefById(id) {
    return this.exerciseDef.find((exercise) => exercise.id === id ) || null
  },
  getAllExercisesDef() {
    return this.exerciseDef
  }
}