package br.com.four.ecom.core.domains.reports.exceptions;

import br.com.four.ecom.core.utils.EcomException.SynchronousException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Exceptions {

    public static class ReportGenerationFailedException extends SynchronousException {

        public ReportGenerationFailedException() {
            super("ECOM-DR-001", "Failed to generate sales report");
        }
    }

    public static class AverageTicketReportException extends SynchronousException {

        public AverageTicketReportException(String customerId) {
            super("ECOM-DR-002", String.format("Failed to generate sales report to customer: %s", customerId));
        }
    }

}
