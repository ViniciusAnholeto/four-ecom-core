package br.com.four.ecom.core.domains.reports.resources;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import br.com.four.ecom.core.domains.reports.models.ReportModel;

public interface MonthlyReport {
    /**
     * Generates a monthly report based on the provided request.
     *
     * @param input The request containing the parameters for generating the monthly report.
     * @return The generated monthly report response.
     */
    ReportModel execute(ReportInput input);
}
