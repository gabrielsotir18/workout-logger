<template>
  <GridLayout
    columns="*" rows="50, *" class="base-card"
    @tap="addSets"
  >
    <Label
      :text="exercise.name" col="0" row="0"
      class="exercise-title"
    />
    <StackLayout col="0" row="1">
      <SetRow
        v-for="set in sets"
        :key="set.id"
        :set="set"
      />
    </Stacklayout>
  </GridLayout>
</template>

<script>
import SetService from '../services/SetService.js'
import ExerciseDefService from '~/services/ExerciseDefService.js'
import SetRow from './SetRow.vue'
import AddSets from '~/pages/AddSets.vue'
import ExerciseService from '~/services/ExerciseService'

export default {
  components: { SetRow },
  props: ['exercise', 'workout'],
  data() {
    return {
      sets: []
    }
  },
  watch: {
    exercise(newVal) {
      this.sets = SetService.getSetsForExercise(newVal.id)
    }
  },
  mounted() {
    this.sets = SetService.getSetsForExercise(this.exercise.id)
  },
  methods: {
    addSets() {
      const exercise = ExerciseService.getExerciseById(this.exercise.id)
      const exerciseDef = ExerciseDefService.getExerciseDefById(
        exercise.exerciseDefId
      )

      this.$navigateTo(AddSets, {
        props: {
          workout: this.workout,
          exerciseDef: exerciseDef,
        },
        transition: {
          name: 'slideLeft',
          duration: 300,
          curve: 'easeIn'
        }
      })
    }
  }
}
</script>

<style scoped>
.base-card {
    border-width: 10px;
    border-style: solid;
    border-color: rgba(33, 32, 32, 0.064);
    border-radius: 30px;
    background-color: rgba(236, 147, 138, 0.478);
    margin: 5 10;
    padding: 10;
}
.exercise-title {
    font-size: 20;
    font-weight: bold;
}
</style>