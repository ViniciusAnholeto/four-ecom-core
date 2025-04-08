package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportRequest {
    private Long userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ReportInput toInput() {
        return new ReportInput();
    }
}
