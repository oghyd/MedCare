package Service;

import dao.interfaces.ConsultationDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import report.dto.DailyRevenueDTO;
import report.dto.MonthlyReportDTO;

/**
 *
 * @author hhgyd
 */
public class ReportService {
    
    private final ConsultationDAO consultationDAO;

    public ReportService(ConsultationDAO consultationDAO) {
        this.consultationDAO = consultationDAO;
    }

    /**
     * Builds a full monthly report:
     *  - total consultations (DONE)
     *  - total revenue (DONE + paid)
     *  - daily revenue breakdown
     */
    public MonthlyReportDTO generateMonthlyReport(int year, int month) throws SQLException {

        // 1) Total consultations in the month
        int totalConsultations = consultationDAO.countConsultationsForMonth(year, month);

        // 2) Total revenue (sum of prix for DONE + paid consultations)
        double totalRevenue = consultationDAO.totalRevenueForMonth(year, month);

        // 3) Daily revenue map -> convert to list of DTOs
        Map<LocalDate, Double> dailyMap = consultationDAO.dailyRevenueForMonth(year, month);
        LinkedList<DailyRevenueDTO> dailyList = new LinkedList<>();

        for (Map.Entry<LocalDate, Double> entry : dailyMap.entrySet()) {
            LocalDate date = entry.getKey();
            double revenue = entry.getValue();
            dailyList.add(new DailyRevenueDTO(date, revenue));
        }

        // 4) Build and return the DTO
        MonthlyReportDTO dto = new MonthlyReportDTO();
        dto.setYear(year);
        dto.setMonth(month);
        dto.setTotalConsultations(totalConsultations);
        dto.setTotalRevenue(totalRevenue);
        dto.setDailyRevenues(dailyList);

        return dto;
    }
    
}
