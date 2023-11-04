<template>
  <Page>
    <ActionBar>
      <GridLayout columns="*, 50, 50" width="100%">
        <Label text="Add Exercise" class="action-bar-title" col="0" />
        <!-- <Button
          text.decode="&#xf783;" col="1" class="fas"
          fontSize="18" @tap="calendarView"
        /> -->
        <Button
          text.decode="&#x2b;" col="2" class="fas"
          fontSize="18" @tap="addExerciseDef"
        />
      </GridLayout>
    </ActionBar>

    <GridLayout columns="50, *" rows="100, *">
      <Label
        col="0" row="0" text.decode="&#xf002;"
        class="fas search-icon"
      />
      <TextField
        v-model="searchPattern" col="1" row="0"
        class="input"
      />
      <ScrollView col="0" colSpan="2" row="1">
        <StackLayout>
          <Label
            v-for="exercise in exercisesDef" :key="exercise.id"
            :text="exercise.name" class="exercise-def" @tap="addSets(exercise)"
          />
        </StackLayout>
      </ScrollView>
    </GridLayout>
  </Page>
</template>

<script>
import ExerciseDefService from '../services/ExerciseDefService.js'
import AddExerciseDef from './AddExerciseDef.vue'

export default {
  props: ['workout'],
  data() {
    return {
      searchPattern: '',
      exercisesDef: [],
    }
  },
  watch: {
    // eslint-disable-next-line no-unused-vars
    searchPattern(newVal) {
      this.exercisesDef = ExerciseDefService.filterByName(this.searchPattern)
    }
  },
  mounted() {
    console.log(this.workout)
    this.exercisesDef = ExerciseDefService.getAllExercisesDef()
  },
  methods: {
    addExerciseDef() {
      console.log('AddExerciseDef')
      this.$navigateTo(AddExerciseDef, {
        transition: {
          name: 'slideRight',
          duration: 300,
          curve: 'easeIn'
        }
      })
    },
    addSets(exercise) {
      console.log(exercise)
    },
  }
}
</script>

<style scoped>
.action-bar-title {
  text-align: left;
  margin-left: 10;
  font-size: 24;
  font-weight: bold;
}

.search-icon {
  text-align: center;
  font-size: 18;
}

.input {
  margin: 25 20 25 10;
  padding: 5;
  border-width: 1 ;
  border-style: solid;
  border-color: #00000046;
  border-radius: 20;
  font-size: 20;
  width: 100%;
}

.exercise-def {
  margin: 5 5 25 5;
  padding: 10 15;
  font-size: 18;
  border-bottom-width: 1;
  border-bottom-style: solid;
  border-bottom-color: rgba(34, 32, 32, 0.189);
}
</style>