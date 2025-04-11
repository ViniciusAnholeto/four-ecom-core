package br.com.four.ecom.core.domains.reports.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthSalesReportModel {
    private Double totalAmountMonthSales;
    private Integer totalOrders;
    private Double averagePerOrder;

}
