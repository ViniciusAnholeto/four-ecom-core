package br.com.four.ecom.core.domains.reports.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReportInput {
    private String customerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
