<template>
  <Page>
    <ActionBar>
      <GridLayout columns="*, 50, 50" width="100%">
        <Label text="Select Workout" class="action-bar-title" col="0" />
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
    <DatePicker v-model="selectedDate" :date="Date.now()" />
  </Page>
</template>

<script>
export default {
  data() {
    return {
      selectedDate: ''
    }
  },
  watch: {
    // eslint-disable-next-line no-unused-vars
    selectedDate(newVal) {
      console.log(this.selectedDate)
      console.log(typeof(this.selectedDate))
    }
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
</style>