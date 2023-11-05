export default {
  workouts: [
    {
      id: 1,
      date: Date.parse('2023-10-26'),
      title: 'Chest'
    },
    {
      id: 2,
      date: Date.parse('2023-10-27'),
      title: 'Back'
    },
    {
      id: 3,
      date: Date.parse('2023-10-28'),
      title: 'Legs'
    },
    {
      id: 4,
      date: Date.parse('2023-10-29'),
      title: ''
    },
    {
      id: 5,
      date: Date.parse('2023-11-4'),
      title: ''
    }
  ],

  getWorkouts() {
    return this.workouts
  },

  getWorkoutById(id) {
    return this.workouts.find((workout) => workout.id === id) || null
  },

  getWorkoutByDate(date) {
    const curDate = Date.parse(date)
    return this.workouts.find((workout) => workout.date === curDate) ||
      this.addWorkout(curDate)
  },

  addWorkout(date) {
    const maxId = Math.max(...this.workouts.map(o => o.id))
    const newWorkout = {
      id: maxId + 1,
      date:date,
      title: ''
    }
    this.workouts.push(newWorkout)
    return newWorkout
  }
}