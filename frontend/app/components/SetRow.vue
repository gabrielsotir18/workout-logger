<template>
  <GridLayout columns="50, 50, *, 40, *, 40" rows="50">
    <Label
      v-if="isPb(set)" col="0" row="0"
      fontSize="20"
      text.decode="&#xf091;" class="fas irrelevant"
    />
    <Label
      v-if="set.note" col="1" row="0"
      fontSize="20" text.decode="&#xf27a;"
      class="fas irrelevant" @tap="openDialog"
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
import SetService from '../services/SetService.js'
import CustomModal from './CustomModal.vue'
// import { Dialogs } from '@nativescript/core'

export default {
  props: ['set'],
  data() {
    return {
      weight: '',
      unit: '',
      weightRegex: /(\d+(?:\.\d+)?)(kg|lbs)/,
      noteDialogVisible: false,
    }
  },
  watch: {
    set(newVal) {
      this.setWeightUnit(newVal)
    }
  },
  mounted() {
    this.setWeightUnit(this.set)
  },
  methods: {
    isPb(set) {
      return SetService.isPersonalBest(set)
    },
    setWeightUnit(set) {
      const matches = set.weight.match(this.weightRegex)
      const[ , weight, unit] = matches
      this.weight = weight
      this.unit = unit
    },
    openDialog() {
      this.$showModal(CustomModal, {
        props: {
          title: 'Set Note',
          initialText: this.set.note,
        }
      }).then((res) => {
        if (res) {
          SetService.updateNote(this.set, res.trim())
        }
      })
    },
    onConfirm(value) {
      console.log('confirm', value)
      this.noteDialogVisible = false
    },
    onCancel() {
      console.log('cancel')
      this.noteDialogVisible = false
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

.custom-dialog-input {
  margin: 10;
}
</style>