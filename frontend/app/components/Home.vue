<template>
  <Page>
    <ActionBar title="Workouts" />

    <GridLayout
      columns="50, *, 50" rows="50, 100, *, auto"
      @swipe="handleSwipe"
    >
      <Label
        class="fas action-btn" text.decode="&#xf060;" row="0"
        col="0" @tap="changeDate('decrease')"
      />
      <Label
        :text="humanReadableDate(date)" class="cur-date" row="0"
        col="1" @tap="changeDate('now')"
      />
      <Label
        class="fas action-btn" text.decode="&#xf061;" row="0"
        col="2" @tap="changeDate('increase')"
      />
      <Label
        :text="curWorkout && curWorkout.title ? curWorkout.title : 'No workout'"
        row="1" col="1"
        textAlignment="center" fontSize="32"
      />
      <ScrollView row="2" col="0" colSpan="3">
        <StackLayout>
          <ExerciseCard
            v-for="exercise in exercises" :key="exercise.id"
            :exercise="exercise"
          />
        </StackLayout>
      </ScrollView>
      <Button
        text="Add Exercise" row="3" col="1"
        class="base-btn" @tap="addExercise"
      />
    </GridLayout>
  </Page>
</template>

<script>
import WorkoutService from '../services/WorkoutService.js'
import ExerciseService from '../services/ExerciseService.js'
import ExerciseCard from './ExerciseCard.vue'

export default {
  components: {
    ExerciseCard,
  },
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
    },
    handleSwipe(swipe) {
      if (swipe.direction === 1) {
        // swipe right, date decrease
        this.changeDate('decrease')
      } else if (swipe.direction === 2) {
        // swipe left, date increase
        this.changeDate('increase')
      }
    }
  }
}
</script>

<style scoped>
.base-btn {
  background-color: #5e2828;
  color: #f1e1e1;
  margin: 20px;
}

.action-btn {
  text-align: center;
  background-color: #c78f85;
}

.cur-date {
  text-align: center;
  padding: 10px;
  font-size: 24;
  font-weight: bold;
  background-color: #ffcfc7;
}

</style>