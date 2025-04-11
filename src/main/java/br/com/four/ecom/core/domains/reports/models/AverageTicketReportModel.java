package br.com.four.ecom.core.domains.reports.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AverageTicketReportModel {
    private String customerId;
    private Double totalValue;
    private Integer totalOrders;
    private Double averageTicket;
}
