package br.com.four.ecom.core.domains.reports.usecases;

import br.com.four.ecom.core.domains.reports.exceptions.Exceptions.ReportGenerationFailedException;
import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import br.com.four.ecom.core.domains.reports.models.BestBuyersReportModel;
import br.com.four.ecom.core.domains.reports.models.ReportModel;
import br.com.four.ecom.core.domains.reports.ports.DatabasePort;
import br.com.four.ecom.core.domains.reports.resources.BestBuyersReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BestBuyersReportImpl implements BestBuyersReport {

    private final DatabasePort databasePort;

    @Override
    public ReportModel execute(Integer bestBuyersCount, ReportInput reportInput) {
        Optional<BestBuyersReportModel> bestBuyersReport =
                databasePort.getBestBuyersReport(bestBuyersCount, reportInput);

        if (bestBuyersReport.isEmpty()) {
            throw new ReportGenerationFailedException();
        }

        return ReportModel.builder()
                .bestBuyersReport(bestBuyersReport)
                .build();
    }
}
