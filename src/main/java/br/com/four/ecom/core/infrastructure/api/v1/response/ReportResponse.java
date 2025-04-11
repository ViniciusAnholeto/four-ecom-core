package br.com.four.ecom.core.infrastructure.api.v1.response;

import br.com.four.ecom.core.domains.reports.models.AverageTicketReportModel;
import br.com.four.ecom.core.domains.reports.models.BestBuyersReportModel;
import br.com.four.ecom.core.domains.reports.models.MonthSalesReportModel;
import br.com.four.ecom.core.domains.reports.models.ReportModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ReportResponse {
    private LocalDateTime createdAt;
    private Optional<MonthSalesReportModel> monthSalesReport;
    private Optional<List<BestBuyersReportModel>> bestBuyersReport;
    private Optional<AverageTicketReportModel> averageTicketReport;

    public ReportResponse(ReportModel reportModel) {
        this.createdAt = LocalDateTime.now();
        this.monthSalesReport = reportModel.getMonthSalesReport();
        this.bestBuyersReport = reportModel.getBestBuyersReport();
        this.averageTicketReport = reportModel.getAverageTicketReport();
    }
}
