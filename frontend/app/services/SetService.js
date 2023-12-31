import ExerciseService from './ExerciseService.js'

export default {
  sets: [
    {
      id: 1,
      exerciseId: 1,
      weight: '20kg',
      reps: 10,
      note: '',
    },
    {
      id: 2,
      exerciseId: 1,
      weight: '20kg',
      reps: 9,
      note: 'ceva'
    },
    {
      id: 3,
      exerciseId: 2,
      weight: '30kg',
      reps: 8,
      note: '',
    },
    {
      id: 4,
      exerciseId: 1,
      weight: '135lbs',
      reps: 6,
      note: 'altceva'
    },
    {
      id: 5,
      exerciseId: 13,
      weight: '20kg',
      reps: 11,
      note: 'altceva'
    },
  ],
  getSetsForExercise(exerciseId) {
    return this.sets.filter((set) => set.exerciseId === exerciseId)
  },
  maxRepsWeight() {
    let maxRepsWeight = {}
    this.sets.forEach((set) => {
      const exerciseId = ExerciseService.getExerciseDef(set.exerciseId)
      if (!maxRepsWeight[exerciseId]) {
        maxRepsWeight[exerciseId] = {}
      }

      const weightReps = maxRepsWeight[exerciseId]

      if (!weightReps[set.weight] ||
        set.reps > weightReps[set.weight]) {
        weightReps[set.weight] = set.reps
      }
    })
    console.log(maxRepsWeight)
    return maxRepsWeight
  },
  isPersonalBest(curSet) {
    const exerciseId = ExerciseService.getExerciseDef(curSet.exerciseId)
    const curBestReps = this.maxRepsWeight()[exerciseId][curSet.weight]
    return curSet.reps >= curBestReps
  },
  updateNote(set, note) {
    const curSet = this.sets.find((s) => s.id === set.id)
    curSet.note = note
  },
  addSet(exercise, weight, reps, note) {
    const maxId = Math.max(...this.sets.map(o => o.id))
    const newSet = {
      id: maxId + 1,
      exerciseId: exercise.id,
      weight: weight,
      reps: reps,
      note: note
    }
    this.sets.push(newSet)
    return newSet
  },
  updateSet(set, weight, reps) {
    this.sets = this.sets.map((obj) => {
      if (obj.id === set.id) {
        return {...obj, weight: weight, reps: reps}
      }
      return obj
    })
  },
  deleteSet(set) {
    this.sets = this.sets.filter((obj) => obj.id !== set.id)
  },
  hasSets(exercise) {
    return this.sets.filter((set) => set.exerciseId === exercise.id).length > 0
  }
}