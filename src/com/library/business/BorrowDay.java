package com.library.business;

public enum BorrowDay {
    SEVEN_DAYS(7),
    TWENTY_ONE_DAYS(21);

    private int day;

    BorrowDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
