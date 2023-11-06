<template>
  <GridLayout columns="50, 50, *, 40, *, 40" rows="50">
    <Label
      v-if="isPb(set)" col="0" row="0"
      fontSize="20"
      text.decode="&#xf091;" class="fas irrelevant"
    />
    <Label
      v-if="mode === 'card' && set.note" col="1" row="0"
      fontSize="20" text.decode="&#xf27a;"
      class="fas irrelevant" @tap="openDialog"
    />
    <Label
      v-else-if="mode === 'basic'" col="1" row="0"
      fontSize="20" text.decode="&#xf27a;"
      class="fas irrelevant" :style="note_type" @tap="openDialog"
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
  props: {
    set: Object,
    mode: {
      type: String,
      default: 'card',
    },
  },
  emits: ['note-update'],
  data() {
    return {
      weight: '',
      unit: '',
      weightRegex: /(\d+(?:\.\d+)?)(kg|lbs)/,
      noteDialogVisible: false,
    }
  },
  computed: {
    note_type() {
      return {
        color: this.set.note ? '#8c4949' : '#b3a8a6',
      }
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
        if (res || res === '') {
          SetService.updateNote(this.set, res.trim())
          this.$emit('note-update')
        }
      })
    },
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