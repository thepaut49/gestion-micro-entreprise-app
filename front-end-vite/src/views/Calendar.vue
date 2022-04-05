<template>
  <section class="calendar-page">
    <header>
      <h2>Calendrier</h2>
    </header>

    <section class="section-select-mont-calendar">
      <BaseSelect
        :options="months"
        v-model="selectedMonth"
        name="selectedMonth"
      />

      <BaseSelect :options="years" v-model="selectedYear" name="selectedYear" />
    </section>

    <section class="month-calendar">
      <div class="calendar-header">Lundi</div>
      <div class="calendar-header">Mardi</div>
      <div class="calendar-header">Mercredi</div>
      <div class="calendar-header">Jeudi</div>
      <div class="calendar-header">Vendredi</div>
      <div class="calendar-header">Samedi</div>
      <div class="calendar-header">Dimanche</div>
      <div v-for="(day, index) in days" :key="index" :class="dayClass(day)">
        {{ day.toLocaleDateString() }}
      </div>
    </section>
  </section>
</template>

<script>
import BaseSelect from "../components/commons/BaseSelect.vue";
import { ref, watchEffect } from "vue";
import {
  monthName,
  getDaysForMonthCalendar,
  getYearsForCalendars,
  getMonthNumber,
} from "../components/calendar/calendarUtils";

export default {
  setup() {
    const months = ref(monthName);
    const today = new Date();
    const selectedMonth = ref(months.value[today.getMonth()]);
    const selectedYear = ref(today.getFullYear());
    const years = getYearsForCalendars(selectedYear.value);
    const days = ref(
      getDaysForMonthCalendar(
        selectedYear.value,
        getMonthNumber(selectedMonth.value)
      )
    );

    function updateDaysForMonthCalendar() {
      days.value = getDaysForMonthCalendar(
        selectedYear.value,
        getMonthNumber(selectedMonth.value)
      );
    }

    function dayClass(day) {
      console.log("day = " + day);
      console.log("selectedMonth = " + selectedMonth.value);
      let classCss = "current-month-day";
      if (
        selectedMonth &&
        day &&
        day.getMonth() !== getMonthNumber(selectedMonth.value)
      ) {
        console.log("not-current-month-day ! " + day);
        classCss = "not-current-month-day";
      }
      return classCss;
    }

    watchEffect(() => {
      updateDaysForMonthCalendar();
    });

    return {
      days,
      months,
      years,
      selectedMonth,
      selectedYear,
      updateDaysForMonthCalendar,
      dayClass,
    };
  },
  components: { BaseSelect },
};
</script>

<style scoped>
.calendar-page {
  display: flex;
  flex-direction: column;
  justify-items: stretch;
  height: 100%;
  margin-left: 0.5em;
  margin-right: 0.5em;
}
.year-calendar {
  display: grid;
}

.section-select-mont-calendar {
  margin-top: 0.5em;
  display: flex;
  flex-grow: 0;
}

.month-calendar {
  padding-top: 0.5em;
  padding-bottom: 0.5em;
  display: grid;
  gap: 0.5em;
  grid: min-content repeat(6, 1fr) / repeat(7, 1fr);
  flex-grow: 1;
}

.current-month-day {
  border: 1px solid #000;
  background-color: blue;
  color: white;
  border-radius: 5px;
}

.not-current-month-day {
  border: 1px solid #000;
  background-color: rgba(0, 162, 255, 0.705);
  color: white;
  border-radius: 5px;
}

.day {
}

.calendar-header {
  font-weight: bold;
  text-align: center;
}
</style>
