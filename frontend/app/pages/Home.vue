<template>
  <!-- eslint-disable-next-line vue/v-on-event-hyphenation -->
  <Page @navigatedTo="setExercies(getWorkout(date))">
    <ActionBar>
      <GridLayout columns="*, 50, 50" width="100%">
        <Label text="Workouts" class="action-bar-title" col="0" />
        <Button
          text.decode="&#xf783;" col="1" class="fas"
          fontSize="18" @tap="calendarView"
        />
        <Button
          text.decode="&#x2b;" col="2" class="fas"
          fontSize="18" @tap="addExercise"
        />
      </GridLayout>
    </ActionBar>

    <GridLayout
      columns="50, *, 50" rows="50, *, auto"
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
      <!-- <Label
        :text="curWorkout && curWorkout.title ? curWorkout.title : 'No workout'"
        row="1" col="1"
        textAlignment="center" fontSize="32"
      /> -->
      <ScrollView
        v-if="exercises.length" row="1" col="0"
        colSpan="3"
        marginTop="10"
      >
        <StackLayout>
          <ExerciseCard
            v-for="exercise in exercises" :key="exercise.id"
            :exercise="exercise"
          />
        </StackLayout>
      </ScrollView>
      <GridLayout
        v-else columns="*" rows="50,50"
        row="1" col="0" colSpan="3"
        marginTop="100"
      >
        <Label
          text.decode="&#xf44b;" row="0" fontSize="40"
          col="0" textAlignment="center" class="fas"
        />
        <Label
          text="No workout today, yet!" row="1"
          col="0" textAlignment="center" fontSize="20"
        />
      </GridLayout>
      <Button
        text="Add Exercise" row="2" col="1"
        class="base-btn" @tap="addExercise"
      />
    </GridLayout>
  </Page>
</template>

<script>
import WorkoutService from '../services/WorkoutService.js'
import ExerciseService from '../services/ExerciseService.js'
import ExerciseCard from '../components/ExerciseCard.vue'
import AddExercise from './AddExercise.vue'
import CalendarView from './CalendarView.vue'

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
      return this.getWorkout(this.date)
    }
  },
  watch: {
    curWorkout(newVal) {
      this.setExercies(newVal)
    }
  },
  mounted() {
    const workout = this.getWorkout(this.date)
    this.setExercies(workout)
  },
  methods: {
    getWorkout(date) {
      return WorkoutService.getWorkoutByDate(this.parsableDate(date))
    },
    setExercies(workout) {
      if (workout) {
        this.exercises = ExerciseService.getExercisesByWorkout(workout.id)
      } else {
        this.exercises = []
      }
    },
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
      this.$navigateTo(AddExercise, {
        props: {
          workout: this.curWorkout ? this.curWorkout : this.date
        },
        transition: {
          name: 'slideTop',
          duration: 300,
          curve: 'easeIn'
        }
      })
    },
    calendarView() {
      console.log('Calendar View')
      // this.$navigateTo(CalendarView, {
      //   transition: {
      //     name: 'slideBottom',
      //     duration: 300,
      //     curve: 'easeIn'
      //   }
      // })
    },
    handleSwipe(swipe) {
      if (swipe.direction === 1) {
        // swipe right, date decrease
        this.changeDate('decrease')
      } else if (swipe.direction === 2) {
        // swipe left, date increase
        this.changeDate('increase')
      } else if (swipe.direction === 4) {
        // swipe up, add exercise
        this.addExercise()
      } else if (swipe.direction === 8) {
        // swipe down, select workout
        this.calendarView()
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
  font-size: 18;
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

.action-bar-title {
  text-align: left;
  margin-left: 10;
  font-size: 24;
  font-weight: bold;
}

</style>