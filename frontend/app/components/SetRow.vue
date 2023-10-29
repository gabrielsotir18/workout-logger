<template>
  <GridLayout columns="50, 50, *, 40, *, 40" rows="50">
    <Label
      v-if="isPb(set)" col="0" row="0"
      fontSize="20"
      text.decode="&#xf091;" class="fas irrelevant"
    />
    <Label
      v-if="set.note" col="1" row="0"
      fontSize="20"
      text.decode="&#xf27a;" class="fas irrelevant"
    />
    <Label
      :text="set.reps" col="2" row="0"
      class="relevant"
    />
    <Label
      text="reps" col="3" row="0"
      class="irrelevant"
    />
    <Label
      :text="weight" col="4" row="0"
      class="relevant"
    />
    <Label
      :text="unit" col="5" row="0"
      class="irrelevant"
    />
  </GridLayout>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import SetService from '../services/SetService.js'

export default {
  props: ['set'],
  data() {
    return {
      weight: '',
      unit: '',
    }
  },
  watch: {
    set(newVal) {
      const matches = newVal.weight.match(/(\d+)([a-zA-Z]+)/)
      const[ , weight, unit] = matches
      this.weight = weight
      this.unit = unit
    }
  },
  mounted() {
    const matches = this.set.weight.match(/(\d+)([a-zA-Z]+)/)
    const[ , weight, unit] = matches
    this.weight = weight
    this.unit = unit
  },
  methods: {
    isPb(set) {
      return SetService.isPersonalBest(set)
    }
  }
}
</script>

<style scoped>
.relevant {
    font-size: 30px;
    font-weight: bold;
    text-align: right;
}

.irrelevant {
    text-align: center;
}
</style>