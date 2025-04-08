package br.com.four.ecom.core.domains.reports.inputs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportInput {
    private Long userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
