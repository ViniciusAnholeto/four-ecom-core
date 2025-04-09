package br.com.four.ecom.core.infrastructure.database;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import br.com.four.ecom.core.domains.reports.models.AverageTicketReportModel;
import br.com.four.ecom.core.domains.reports.models.BestBuyersReportModel;
import br.com.four.ecom.core.domains.reports.models.MonthSalesReportModel;
import br.com.four.ecom.core.domains.reports.ports.DatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportsDatabaseAdapter implements DatabasePort {

    @Override
    public Optional<AverageTicketReportModel> getAverageTicketReport(ReportInput reportInput) {
        return Optional.empty();
    }

    @Override
    public Optional<BestBuyersReportModel> getBestBuyersReport(Integer bestBuyersCount, ReportInput reportInput) {
        return Optional.empty();
    }

    @Override
    public Optional<MonthSalesReportModel> getMonthlyReport(Integer month) {
        return Optional.empty();
    }
}
