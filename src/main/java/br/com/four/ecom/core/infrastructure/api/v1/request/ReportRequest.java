package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import lombok.Data;

@Data
public class ReportRequest {
    private String customerId;
    private DataRangeRequest dataRange;

    public ReportInput toInput() {
        return ReportInput.builder()
                .customerId(customerId)
                .startDate(dataRange.getStartDate())
                .endDate(dataRange.getEndDate())
                .build();
    }
}
