<template>
  <Page>
    <ActionBar title="Workouts" />

    <GridLayout columns="50, *, 50" rows="50, 100, *, auto">
      <Label
        class="fas" text.decode="&#xf060;"
        textAlignment="center" backgroundColor="#c78f85" row="0"
        col="0"
        @tap="changeDate('decrease')"
      />
      <Label
        :text="humanReadableDate(date)"
        textAlignment="center"
        padding="10"
        row="0"
        col="1"
        fontSize="24"
        backgroundColor="#ffcfc7"
        @tap="changeDate('now')"
      />
      <Label
        class="fas" text.decode="&#xf061;"
        row="0" col="2" backgroundColor="#c78f85"
        textAlignment="center" @tap="changeDate('increase')"
      />
      <Label
        :text="curWorkout ? curWorkout.title : 'No workout'" row="1" col="1"
        textAlignment="center" fontSize="32"
      />
      <ListView
        for="exercise in exercises" row="2" col="0"
        colSpan="3" margin="10"
      >
        <v-template>
          <Label :text="exercise.name" />
        </v-template>
      </ListView>
      <Button
        text="Add Exercise" row="3" col="1"
        backgroundColor="#5e2828" color="#FFFFFF" margin="20"
        @tap="addExercise"
      />
    </GridLayout>
  </Page>
</template>

<script>
import WorkoutService from '../services/WorkoutService.js'
import ExerciseService from '../services/ExerciseService.js'

export default {
  data() {
    return {
      workouts: WorkoutService.getWorkouts(),
      date: new Date(),
      exercises: [],
    }
  },
  computed: {
    curWorkout() {
      const workout = WorkoutService.getWorkoutByDate(
        this.parsableDate(this.date)
      )
      return workout
    }
  },
  watch: {
    curWorkout(newVal) {
      if (newVal) {
        this.exercises = ExerciseService.getExercisesByWorkout(newVal.id)
        console.log(this.exercises)
      } else {
        this.exercises = []
      }
    }
  },
  mounted() {
    const workout = WorkoutService.getWorkoutByDate(
      this.parsableDate(this.date)
    )
    if (workout) {
      this.exercises = ExerciseService.getExercisesByWorkout(workout.id)
    } else {
      this.exercises = []
    }
  },
  methods: {
    printDate(item) {
      console.log(this.humanReadableDate(item.date))
    },
    humanReadableDate(timestamp) {
      // eslint-disable-next-line max-len
      return `${timestamp.getDate()}-${timestamp.getMonth() + 1}-${timestamp.getFullYear()}`
    },
    parsableDate(timestamp) {
      // eslint-disable-next-line max-len
      return `${timestamp.getFullYear()}-${timestamp.getMonth() + 1}-${timestamp.getDate()}`
    },
    changeDate(mode) {
      let newDate
      if (mode === 'now') {
        newDate = new Date()
      } else {
        newDate = new Date(this.date)
        if (mode === 'decrease') {
          newDate.setDate(newDate.getDate() - 1)
        } else if (mode === 'increase') {
          newDate.setDate(newDate.getDate() + 1)
        }
      }
      this.date = newDate
    },
    addExercise() {
      console.log('Add Exercise')
    }
  }
}
</script>