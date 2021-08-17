package com.company.backendcourses.helpers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CarnetGenerator {

    private String letter;
    private String year;
    private int quantity;
    private String id;

    public String generate(int quantity) {
        this.quantity = quantity + 1;
        this.year = String.valueOf(this.getYear()).substring(0,4);
        return this.year +  "-" + this.quantity;
    }

    public int getYear(){
        Date date = new Date();
        ZoneId timeZone = ZoneId.systemDefault();
        return date.toInstant().atZone(timeZone).toLocalDate().getYear();
    }
}
