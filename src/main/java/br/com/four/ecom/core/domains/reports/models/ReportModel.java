package br.com.four.ecom.core.domains.reports.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@Builder
public class ReportModel {
    private LocalDateTime createdAt;
    private Optional<MonthSalesReportModel> monthSalesReport;
    private Optional<List<BestBuyersReportModel>> bestBuyersReport;
    private Optional<AverageTicketReportModel> averageTicketReport;
}
