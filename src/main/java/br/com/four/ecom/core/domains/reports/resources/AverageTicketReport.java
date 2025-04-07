package br.com.four.ecom.core.domains.reports.resources;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import br.com.four.ecom.core.domains.reports.models.ReportModel;


public interface AverageTicketReport {
    /**
     * Generates an average ticket report based on the provided request.
     *
     * @param input The request containing the parameters for generating the report.
     * @return The generated report response.
     */
    ReportModel execute(ReportInput input);
}
