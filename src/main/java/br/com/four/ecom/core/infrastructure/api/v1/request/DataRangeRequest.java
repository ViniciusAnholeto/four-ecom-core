package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DataRangeRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ReportInput toInput() {
        return ReportInput.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
