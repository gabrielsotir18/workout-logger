<template>
  <Page>
    <ActionBar>
      <GridLayout columns="*, 50, 50" width="100%">
        <Label text="Add Defined Exercise" class="action-bar-title" col="0" />
        <!-- <Button
          text.decode="&#xf783;" col="1" class="fas"
          fontSize="18" @tap="calendarView"
        />
        <Button
          text.decode="&#x2b;" col="2" class="fas"
          fontSize="18" @tap="addExerciseDef"
        /> -->
      </GridLayout>
    </ActionBar>

    <GridLayout columns="*" rows="50, 100, 50, 100, *" marginTop="20">
      <Label
        col="0" row="0" text="Exercise Name"
        class="input-label"
      />
      <TextField
        v-model="exerciseName" col="0" row="1"
        class="exercise-name"
      />
      <Label
        col="0" row="2" text="Muscle Group"
        class="input-label"
      />
      <ListPicker
        v-model="muscleGroup" col="0" row="3"
        :items="muscleGroups" class="muscle-group-select"
      />
      <StackLayout col="0" row="4" orientation="horizontal">
        <Button
          text="Cancel" col="0" row="2"
          class="dialog-button" @tap="onCancel"
        />
        <Button
          text="OK" col="1" row="2"
          class="dialog-button" @tap="onConfirm"
        />
      </StackLayout>
    </GridLayout>
  </Page>
</template>

<script>
import ExerciseDefService from '~/services/ExerciseDefService'

export default {
  data() {
    return {
      exerciseName: '',
      muscleGroup: '',
      muscleGroups: ['Chest', 'Back', 'Legs', 'Biceps']
    }
  },
  methods: {
    onCancel() {
      this.$navigateBack()
    },
    onConfirm() {
      if (isNaN(this.muscleGroup)) this.muscleGroup = 0
      if (this.exerciseName) {
        ExerciseDefService.addExercise(
          this.exerciseName, this.muscleGroups[this.muscleGroup]
        )
        this.$navigateBack()
      }
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
  margin: 10;
  font-size: 20;
  font-weight: bold;
}

.exercise-name {
  margin: 20 10;
  padding: 10;
  border-width: 1 ;
  border-style: solid;
  border-color: #00000046;
  border-radius: 20;
  font-size: 20;
}

.muscle-group-select {
  margin: 10;
  color: #f07d7d;
}

.dialog-button {
  background-color: #f29d9d;
  margin: 10;
  width: 50%;
  color: rgb(59, 55, 55);
}
</style>