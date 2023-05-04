package org.yearup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Transactions {

    private LocalDate date;
    private LocalTime time;
    private String description;

    private String vendor;
    private double amount;


    public Transactions(LocalDate date, LocalTime time, String description, String vendor, double amount) {

        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    private double monthToDateTotal;
    private String key;
    private Double value;

    public Transactions(String key, Double value) {

        this.key = key;
        this.value = value;
    }

    public String getKey() { return key; }

    public Double getValue() { return value; }

    public void setMonthToDateTotal(double monthToDateTotal) {
        this.monthToDateTotal = monthToDateTotal;
    }

    public double getMonthToDateTotal() {
        return monthToDateTotal;
    }
}
