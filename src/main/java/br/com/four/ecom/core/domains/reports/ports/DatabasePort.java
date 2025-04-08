package br.com.four.ecom.core.domains.reports.ports;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import br.com.four.ecom.core.domains.reports.models.AverageTicketReportModel;
import br.com.four.ecom.core.domains.reports.models.BestBuyersReportModel;
import br.com.four.ecom.core.domains.reports.models.MonthSalesReportModel;

import java.util.Optional;

public interface DatabasePort {
    Optional<AverageTicketReportModel> getAverageTicketReport(ReportInput reportInput);

    Optional<BestBuyersReportModel> getBestBuyersReport(Integer bestBuyersCount, ReportInput reportInput);

    Optional<MonthSalesReportModel> getMonthlyReport(Integer month);
}
