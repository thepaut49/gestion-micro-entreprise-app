<template>
  <div class="calendar-page">
    <section class="section-select-mont-calendar">
      <BaseSelect
        :options="months"
        v-model="selectedMonth"
        label="Selectionner le mois"
        name="selectedMonth"
        @change="updateDaysForMonthCalendar()"
      />

      <BaseSelect
        :options="years"
        v-model="selectedYear"
        label="Selectionner l'année"
        name="selectedYear"
        @change="updateDaysForMonthCalendar()"
      />
    </section>

    <section class="month-calendar">
      <div class="calendar-header">Lundi</div>
      <div class="calendar-header">Mardi</div>
      <div class="calendar-header">Mercredi</div>
      <div class="calendar-header">Jeudi</div>
      <div class="calendar-header">Vendredi</div>
      <div class="calendar-header">Samedi</div>
      <div class="calendar-header">Dimanche</div>
      <div v-for="(day, index) in days" :key="index" class="day">
        {{ day.toLocaleDateString() }}
      </div>
    </section>
  </div>
</template>

<script>
import BaseSelect from "../components/commons/BaseSelect.vue";
import { ref, computed } from "vue";
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

    return {
      days,
      months,
      years,
      selectedMonth,
      selectedYear,
      updateDaysForMonthCalendar,
    };
  },
  components: { BaseSelect },
};
</script>

<style scoped>
.calendar-page {
  display: flex;
  flex-direction: column;
}
.calendar-section {
  width: 100%;
}
.year-calendar {
  display: grid;
}

.section-select-mont-calendar {
  margin-top: 0.5em;
  display: flex;
}

.month-calendar {
  padding-top: 0.5em;
  padding-bottom: 0.5em;
  display: grid;
  gap: 0.5em;
  grid: min-content repeat(5, 1fr) / repeat(7, 1fr);
  justify-items: stretch;
  align-items: stretch;
  flex-grow: 4;
}

.month-calendar div {
  border: 1px solid #000;
  background-color: blue;
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
