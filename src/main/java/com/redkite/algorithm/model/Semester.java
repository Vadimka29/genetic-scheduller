package com.redkite.algorithm.model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Semester {
    protected final LocalDate start;
    protected final LocalDate end;
    private List<Day> days;

    public Semester(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
        fillDays();
    }

    //TODO duplicated
    private void fillDays() {
        int numberOfDays = (int) ChronoUnit.DAYS.between(start, end);
        LocalDate curr = start;
        days = new ArrayList<>();
        for (int i = 0; i <= numberOfDays; i++) {
            days.add(new Day(curr));
            curr = curr.plusDays(1);
        }
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public List<Day> getDays() {
        return new ArrayList<>(days);
    }

    /*
    * Change time limit for specified day by date
    * */
    public boolean changeLimit( LocalDate date, Integer hours) {
        Optional<Day> day = days.stream()
                .filter(d -> d.getDate().isEqual(date))
                .findFirst();

        if (day.isPresent()) {
            day.get().changeLimit(hours);
            return true;
        }
        return false;

    }
}
