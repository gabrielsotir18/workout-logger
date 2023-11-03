<template>
  <GridLayout columns="*, *" rows="*, *, *" class="dialog-container">
    <Label
      :text="title" class="dialog-title" col="0"
      colSpan="2" row="0"
    />
    <TextField
      v-model="inputValue" col="0" colSpan="2"
      row="1" class="dialog-input"
    />
    <Button
      text="Cancel" col="0" row="2"
      class="dialog-button" @tap="onCancel"
    />
    <Button
      text="OK" col="1" row="2"
      class="dialog-button" @tap="onConfirm"
    />
  </GridLayout>
</template>

<script>
export default {
  props: ['title', 'initialText'],
  data() {
    return {
      inputValue: this.initialText,
    }
  },
  methods: {
    onConfirm() {
      this.$modal.close(this.inputValue)
    },
    onCancel() {
      this.$modal.close(undefined)
    },
  },
}
</script>

<style scoped>
.dialog-container {
  width: 300;
  height: auto;
  padding: 10;
  box-shadow: 0 0 10 2 #000000;
  /* border-radius: 20; */
}

.dialog-title {
  font-size: 20;
  font-weight: bold;
}

.dialog-input {
  margin: 10 5;
  padding: 5;
  border: 1 solid #000;
  border-radius: 5;
  font-size: 18;
}

.dialog-button {
  background-color: #f29d9d;
  margin: 1;
}
</style>
