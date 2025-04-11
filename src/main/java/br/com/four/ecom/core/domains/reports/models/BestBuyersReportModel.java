package br.com.four.ecom.core.domains.reports.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestBuyersReportModel {
    private String customerId;
    private Double totalSpent;
}
