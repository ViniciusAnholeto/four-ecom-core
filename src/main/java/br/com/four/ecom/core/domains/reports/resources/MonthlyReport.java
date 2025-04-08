package br.com.four.ecom.core.domains.reports.resources;

import br.com.four.ecom.core.domains.reports.models.ReportModel;

public interface MonthlyReport {
    /**
     * Generates a monthly report based on the provided request.
     *
     * @return The generated monthly report response.
     */
    ReportModel execute();
}
