package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;

public class ReportRequest {

    public ReportInput toInput() {
        return new ReportInput();
    }
}
