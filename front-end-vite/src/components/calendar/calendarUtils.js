export const monthName = [
  "Janvier",
  "Février",
  "Mars",
  "Avril",
  "Mai",
  "Juin",
  "Juillet",
  "Août",
  "Septembre",
  "Octobre",
  "Novembre",
  "Décembre",
];

export const getMonthNumber = (month) => {
  for (let index = 0; index < 12; index++) {
    if (month === monthName[index]) {
      return index;
    }
  }
  return 0;
};

export const getDaysForMonthCalendar = (year, month) => {
  let listOfDaysForMonthCalendar = [];
  const firstDayOfMonth = new Date(year, month, 1);
  let firstDay = firstDayOfMonth;
  // cas le premier jour du mois est un lundi
  while (firstDay.getDay() != 1) {
    firstDay.setDate(firstDay.getDate() - 1);
  }
  listOfDaysForMonthCalendar.push(new Date(firstDay.getTime()));
  let newDay = firstDay;
  for (let index = 0; index < 41; index++) {
    newDay.setDate(newDay.getDate() + 1);
    listOfDaysForMonthCalendar.push(new Date(newDay.getTime()));
  }

  return listOfDaysForMonthCalendar;
};

export const getYearsForCalendars = (year) => {
  let yearsForCalendars = [];
  for (let i = 25; i >= 0; i--) {
    yearsForCalendars.push(year - i);
  }
  for (let j = 1; j < 25; j++) {
    yearsForCalendars.push(year + j);
  }
  return yearsForCalendars;
};
