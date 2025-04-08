package br.com.four.ecom.core.domains.reports.usecases;

import br.com.four.ecom.core.domains.reports.exceptions.Exceptions.AverageTicketReportException;
import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import br.com.four.ecom.core.domains.reports.models.AverageTicketReportModel;
import br.com.four.ecom.core.domains.reports.models.ReportModel;
import br.com.four.ecom.core.domains.reports.ports.DatabasePort;
import br.com.four.ecom.core.domains.reports.resources.AverageTicketReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AverageTicketReportImpl implements AverageTicketReport {

    private final DatabasePort databasePort;

    @Override
    public ReportModel execute(ReportInput input) {
        Optional<AverageTicketReportModel> averageTicketReport = databasePort.getAverageTicketReport(input);

        if (averageTicketReport.isEmpty()) {
            throw new AverageTicketReportException(input.getUserId());
        }

        return ReportModel.builder()
                .averageTicketReport(averageTicketReport)
                .build();
    }
}
