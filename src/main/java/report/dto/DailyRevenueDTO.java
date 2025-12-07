package report.dto;

import java.time.LocalDate;

/**
 *
 * @author hhgyd
 */
public class DailyRevenueDTO {

    private LocalDate date;
    private double revenue;

    public DailyRevenueDTO() {
    }

    public DailyRevenueDTO(LocalDate date, double revenue) {
        this.date = date;
        this.revenue = revenue;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "DailyRevenueDTO{ " +
                "date = " + date +
                ", revenue = " + revenue +
                " }";   
    }

}
