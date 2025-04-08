package br.com.four.ecom.core.domains.reports.usecases;

import br.com.four.ecom.core.domains.reports.exceptions.Exceptions.ReportGenerationFailedException;
import br.com.four.ecom.core.domains.reports.models.MonthSalesReportModel;
import br.com.four.ecom.core.domains.reports.models.ReportModel;
import br.com.four.ecom.core.domains.reports.ports.DatabasePort;
import br.com.four.ecom.core.domains.reports.resources.MonthlyReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonthlyReportImpl implements MonthlyReport {

    private final DatabasePort databasePort;
    private final Integer month = LocalDateTime.now().getMonthValue();

    @Override
    public ReportModel execute() {
        Optional<MonthSalesReportModel> monthSalesReport = databasePort.getMonthlyReport(month);

        if (monthSalesReport.isEmpty()) {
            throw new ReportGenerationFailedException();
        }

        return ReportModel.builder()
                .monthSalesReport(monthSalesReport)
                .build();
    }
}
