package br.com.four.ecom.core.domains.reports.usecases;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import br.com.four.ecom.core.domains.reports.models.ReportModel;
import br.com.four.ecom.core.domains.reports.resources.MonthlyReport;

public class MonthlyReportImpl implements MonthlyReport {

    @Override
    public ReportModel execute(ReportInput input) {
        // Implement the logic for generating the average ticket report
        // This is a placeholder implementation
        System.out.println("Generating average ticket report...");

        return new ReportModel();
    }
}
