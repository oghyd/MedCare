/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;

import Service.ReportService;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import report.dto.DailyRevenueDTO;
import report.dto.MonthlyReportDTO;

/**
 *
 * @author hhgyd
 */
public class ReportGenerator {


    private final ReportService reportService;

    public ReportGenerator(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Builds a MonthlyReportDTO for a given month/year.
     */
    public MonthlyReportDTO generateMonthlyReport(int year, int month) throws SQLException {
        return reportService.generateMonthlyReport(year, month);
    }

    /**
     * Converts a MonthlyReportDTO to a CSV string.
     *
     * Example output:
     * year;month;totalConsultations;totalRevenue
     * 2025;3;42;12345.50
     *
     * date;revenue
     * 2025-03-01;300.0
     * 2025-03-02;0.0
     * ...
     */
    public String toCsv(MonthlyReportDTO report) {
        StringBuilder sb = new StringBuilder();

        // Summary line
        sb.append("year;month;totalConsultations;totalRevenue").append("\n");
        sb.append(report.getYear()).append(";")
          .append(report.getMonth()).append(";")
          .append(report.getTotalConsultations()).append(";")
          .append(String.format("%.2f", report.getTotalRevenue()))
          .append("\n\n");

        // Daily breakdown
        sb.append("date;revenue").append("\n");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        if (report.getDailyRevenues() != null) {
            for (DailyRevenueDTO daily : report.getDailyRevenues()) {
                String dateStr = daily.getDate() != null
                        ? daily.getDate().format(formatter)
                        : "";
                sb.append(dateStr).append(";")
                  .append(String.format("%.2f", daily.getRevenue()))
                  .append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Human-readable text version for logs / console.
     */
    public String prettyPrint(MonthlyReportDTO report) {
        StringBuilder sb = new StringBuilder();

        sb.append("=== Monthly Report ===\n");
        sb.append("Year: ").append(report.getYear()).append("\n");
        sb.append("Month: ").append(report.getMonth()).append("\n");
        sb.append("Total consultations: ").append(report.getTotalConsultations()).append("\n");
        sb.append("Total revenue: ").append(String.format("%.2f", report.getTotalRevenue())).append("\n\n");

        sb.append("Daily breakdown:\n");
        if (report.getDailyRevenues() != null && !report.getDailyRevenues().isEmpty()) {
            for (DailyRevenueDTO daily : report.getDailyRevenues()) {
                sb.append("  ")
                  .append(daily.getDate())
                  .append(" -> ")
                  .append(String.format("%.2f", daily.getRevenue()))
                  .append("\n");
            }
        } else {
            sb.append("  (no data)\n");
        }

        return sb.toString();
    }

    
}
