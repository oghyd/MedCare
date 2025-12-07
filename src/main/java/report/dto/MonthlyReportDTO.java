/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report.dto;

import java.util.LinkedList;

/**
 *
 * @author hhgyd
 */
public class MonthlyReportDTO {
    
    private int year;
    private int month;

    private int totalConsultations;
    private double totalRevenue;

    private LinkedList<DailyRevenueDTO> dailyRevenues;

    public MonthlyReportDTO() {
    }

    public MonthlyReportDTO(int year, int month, int totalConsultations, double totalRevenue,
                            LinkedList<DailyRevenueDTO> dailyRevenues) {
        this.year = year;
        this.month = month;
        this.totalConsultations = totalConsultations;
        this.totalRevenue = totalRevenue;
        this.dailyRevenues = dailyRevenues;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getTotalConsultations() {
        return totalConsultations;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public LinkedList<DailyRevenueDTO> getDailyRevenues() {
        return dailyRevenues;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setTotalConsultations(int totalConsultations) {
        this.totalConsultations = totalConsultations;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setDailyRevenues(LinkedList<DailyRevenueDTO> dailyRevenues) {
        this.dailyRevenues = dailyRevenues;
    }

    @Override
    public String toString() {
        return "MonthlyReportDTO{ " +
                "year = " + year +
                ", month = " + month +
                ", totalConsultations = " + totalConsultations +
                ", totalRevenue = " + totalRevenue +
                ", dailyRevenues = " + dailyRevenues +
                " }";
    }
}
