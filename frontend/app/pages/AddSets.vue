<template>
  <Page>
    <ActionBar>
      <GridLayout columns="*, 50, 50" width="100%">
        <Label :text="exerciseDef.name" class="action-bar-title" col="0" />
        <!-- <Button
          text.decode="&#xf783;" col="1" class="fas"
          fontSize="18" @tap="calendarView"
        /> -->
        <!-- <Button
          text.decode="&#x2b;" col="2" class="fas"
          fontSize="18" @tap="addExerciseDef"
        /> -->
      </GridLayout>
    </ActionBar>

    <GridLayout columns="*" rows="50, 80, 50, 80, 100, *" marginTop="20">
      <Label
        :text="`Weight(${unit})`" col="0" row="0"
        class="input-label" @tap="changeUnit"
      />
      <StackLayout
        col="0" row="1" orientation="horizontal"
        class="qty-btns"
      >
        <Button
          text="-"
          class="qty-button" @tap="modifyWeight('sub')"
        />
        <TextField v-model="weight" class="weight" keyboardType="number" />
        <Button
          text="+"
          class="qty-button" @tap="modifyWeight('add')"
        />
      </StackLayout>
      <Label
        text="Reps" col="0" row="2"
        class="input-label"
      />
      <StackLayout
        col="0" row="3" orientation="horizontal"
        class="qty-btns"
      >
        <Button
          text="-"
          class="qty-button" @tap="modifyReps('sub')"
        />
        <TextField v-model="reps" class="reps" keyboardType="number" />
        <Button
          text="+"
          class="qty-button" @tap="modifyReps('add')"
        />
      </StackLayout>
      <StackLayout
        col="0" row="4" orientation="horizontal"
        marginTop="10"
      >
        <Button
          :text="negative_text"
          class="negative-btn" @tap="negative"
        />
        <Button
          :text="positive_text"
          class="positive-btn" @tap="positive"
        />
      </StackLayout>
      <ScrollView col="0" row="5" marginTop="10">
        <StackLayout>
          <ContentView
            v-for="set in curSets" :key="set.id"
            @tap="setWeightReps(set)"
          >
            <SetRow
              :set="set" :style="setRow" :mode="'basic'"
              :class="{ 'checked': set.checked }"
              @note-update="setCurSets(set)"
            />
          </ContentView>
        </StackLayout>
      </ScrollView>
    </GridLayout>
  </Page>
</template>

<script>
import WorkoutService from '~/services/WorkoutService.js'
import SetService from '~/services/SetService.js'
import ExerciseService from '~/services/ExerciseService.js'
import SetRow from '~/components/SetRow.vue'

export default {
  components: {
    SetRow,
  },
  props: ['workout', 'exerciseDef'],
  data() {
    return {
      curWorkout: null,
      curSets: [],
      unit: 'kg',
      weight: 0.0,
      reps: 0,
      weightRegex: /(\d+(?:\.\d+)?)(kg|lbs)/,
      negative_text: 'Clear',
      positive_text: 'Add',
      setRow: {
        margin: '0 25',
      }
    }
  },
  computed: {
    curExercise() {
      return ExerciseService.getExercise(this.curWorkout, this.exerciseDef)
    },
  },
  watch: {
    weight(newVal) {
      if (newVal < 0 || !newVal) {
        this.weight = 0.0
      }
    },
    reps(newVal) {
      if (newVal < 0 || !newVal) {
        this.reps = 0
      }
    }
  },
  mounted() {
    this.setCurWorkout()
    this.setCurSets()
  },
  methods: {
    parsableDate(timestamp) {
      // eslint-disable-next-line max-len
      return `${timestamp.getFullYear()}-${timestamp.getMonth() + 1}-${timestamp.getDate()}`
    },
    setCurWorkout() {
      if (!this.workout.id) {
        // no workout object passed, just a date
        this.curWorkout = WorkoutService.addWorkout(
          Date.parse(this.parsableDate(this.workout))
        )
      } else {
        this.curWorkout = this.workout
      }
    },
    setCurSets(updateRepsWeight) {
      this.curSets = SetService.getSetsForExercise(this.curExercise.id).map(
        (obj) => {return {...obj, checked: false}}
      )
      if (updateRepsWeight) {
        this.setWeightReps(updateRepsWeight)
      }
    },
    changeUnit() {
      const unitMapping = {
        'kg': 'lbs',
        'lbs': 'kg'
      }
      this.unit = unitMapping[this.unit]
    },
    modifyWeight(mode) {
      if (mode === 'add') {
        this.weight = parseFloat(this.weight) + 2.5
      } else if (mode === 'sub') {
        this.weight = parseFloat(this.weight) - 2.5
      }
    },
    modifyReps(mode) {
      if (mode === 'add') {
        this.reps = parseInt(this.reps) + 1
      } else if (mode === 'sub') {
        this.reps = parseInt(this.reps) - 1
      }
    },
    negative() {
      if (this.negative_text === 'Delete') {
        SetService.deleteSet(this.curSets.find((obj) => obj.checked === true))
        this.setCurSets()
      }

      this.weight = 0.0
      this.reps = 0
    },
    positive() {
      if (this.reps < 1) return

      if (this.positive_text === 'Add') {
        SetService.addSet(
          this.curExercise,
          this.weight + this.unit,
          this.reps,
          ''
        )
        this.setCurSets()
      } else {
        const set = this.curSets.find((obj) => obj.checked === true)
        SetService.updateSet(
          set,
          this.weight + this.unit,
          this.reps,
        )
        this.setCurSets(set)
      }
    },
    setWeightReps(set) {
      this.curSets.map((obj) => {
        if (obj.id !== set.id) {
          obj.checked = false
        }
      })

      if (!set.checked) {
        this.reps = set.reps

        const matches = set.weight.match(this.weightRegex)
        const[ , weight, unit] = matches
        this.weight = weight
        this.unit = unit

        this.negative_text = 'Delete'
        this.positive_text = 'Update'
      } else {
        this.reps = 0
        this.weight = 0.0
        this.unit = 'kg'
        this.negative_text = 'Clear'
        this.positive_text = 'Add'
      }
      set.checked = !set.checked
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

.input-label {
  margin: 10 20;
  font-size: 20;
  font-weight: bold;
  border-bottom-color: rgba(38, 36, 36, 0.507);
  border-bottom-style: solid;
  border-bottom-width: 1px;
}

.qty-btns {
    margin-left: 25%;
}

.qty-button {
  font-size: 32;
  background-color: #ffcfc7;
  height: 60;
  width: 70;
  margin: 0;
  /* border-radius: 1; */
}

.reps, .weight {
    font-size: 26;
    width: 80;
    text-align: center;
    border-style: solid;
    border-width: 2px;
    height: 60;
    margin: 0;
}

.negative-btn, .positive-btn {
    width: 40%;
    margin-left: 30;
    margin-right: 0;
    height: 50;
    border-radius: 10;
    font-size: 16;
    font-weight: bold;
}

.positive-btn {
    color: #f1e1e1;
    background-color: #5e2828;
}

.negative-btn {
    color: #362f2f;
    background-color: #b3a8a6;
}

.checked {
  background-color: rgba(197, 169, 169, 0.532);
}
</style>