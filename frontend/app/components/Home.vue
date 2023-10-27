<template>
    <Page>
        <ActionBar title="Workouts" />

        <GridLayout columns="50, *, 50" rows="50, 100">
            <Label class="fas" text.decode="&#xf060;" padding=10 textAlignment="center" backgroundColor="#c78f85" row="0" col="0" @tap="changeDate('decrease')"/>
            <Label 
                :text="humanReadableDate(date)" 
                textAlignment="center" 
                padding=10
                row="0" 
                col="1"
                fontSize="24"
                @tap="changeDate('now')" 
                backgroundColor="#ffcfc7"
            />
            <Label class="fas" text.decode="&#xf061;" padding=10 row="0" col="2" backgroundColor="#c78f85" textAlignment="center" @tap="changeDate('increase')" />
            <Label :text="curWorkout" row="1" col="1" textAlignment="center" fontSize="32" />
        </GridLayout>
    </Page>
</template>

<script>
import WorkoutService from '../services/WorkoutService.js'

export default {
    data() {
        return {
            workouts: WorkoutService.getWorkouts(),
            date: new Date()
        };
    },
    computed: {
        curWorkout() {
            const workout = WorkoutService.getWorkoutByDate(this.humanReadableDate(this.date))
            return workout ? workout.title : 'No workout'
        }
    },
    methods: {
        printDate(item) {
            console.log(this.humanReadableDate(item.date))
        },
        humanReadableDate(timestamp) {
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
    }
};
</script>