package br.com.four.ecom.core.domains.reports.usecases;

import br.com.four.ecom.core.domains.reports.models.ReportModel;
import br.com.four.ecom.core.domains.reports.resources.BestBuyersReport;

public class BestBuyersReportImpl implements BestBuyersReport {

    @Override
    public ReportModel execute(Integer bestBuyersCount) {
        // Implement the logic for generating the average ticket report
        // This is a placeholder implementation
        System.out.println("Generating average ticket report...");

        return new ReportModel();
    }
}
