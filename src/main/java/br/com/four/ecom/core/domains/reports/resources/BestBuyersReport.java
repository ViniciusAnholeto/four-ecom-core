package br.com.four.ecom.core.domains.reports.resources;

import br.com.four.ecom.core.domains.reports.models.ReportModel;

public interface BestBuyersReport {
    /**
     * Generates the best buyers report based on the provided count.
     *
     * @param bestBuyersCount The number of best buyers to include in the report.
     * @return best buyers report response.
     */
    ReportModel execute(Integer bestBuyersCount);
}
