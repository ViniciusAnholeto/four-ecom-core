package br.com.four.ecom.core.infrastructure.database;

import br.com.four.ecom.core.domains.reports.inputs.ReportInput;
import br.com.four.ecom.core.domains.reports.models.AverageTicketReportModel;
import br.com.four.ecom.core.domains.reports.models.BestBuyersReportModel;
import br.com.four.ecom.core.domains.reports.models.MonthSalesReportModel;
import br.com.four.ecom.core.domains.reports.ports.DatabasePort;
import br.com.four.ecom.core.infrastructure.database.entities.Order;
import br.com.four.ecom.core.infrastructure.database.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportsDatabaseAdapter implements DatabasePort {

    private final OrdersRepository ordersRepository;

    @Override
    public Optional<AverageTicketReportModel> getAverageTicketReport(ReportInput reportInput) {
        log.info("Generating average ticket report for customer: {} between {} and {}",
                reportInput.getCustomerId(), reportInput.getStartDate(), reportInput.getEndDate());

        try {
            List<Order> userOrders = ordersRepository.findByUserIdAndDateRange(
                    reportInput.getCustomerId(), reportInput.getStartDate(), reportInput.getEndDate());

            Double totalValue = userOrders.stream()
                    .mapToDouble(Order::getTotalValue)
                    .sum();

            Integer totalOrders = userOrders.size();

            Double averageTicket = totalOrders > 0
                    ? totalValue / totalOrders
                    : 0;

            return Optional.of(new AverageTicketReportModel(reportInput.getCustomerId(), totalValue, totalOrders, averageTicket));
        } catch (Exception e) {
            log.error("Error while generating average ticket report: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<BestBuyersReportModel>> getBestBuyersReport(Integer bestBuyersCount, ReportInput reportInput) {
        log.info("Generating top {} buyers report between {} and {}",
                bestBuyersCount, reportInput.getStartDate(), reportInput.getEndDate());

        try {
            Pageable pageable = PageRequest.of(0, bestBuyersCount);
            List<Object[]> results = ordersRepository.findTopBuyersByDateRange(
                    reportInput.getStartDate(), reportInput.getEndDate(), pageable);

            List<BestBuyersReportModel> bestBuyers = results.stream()
                    .map(result -> new BestBuyersReportModel(
                            (String) result[0],
                            (Double) result[1]
                    ))
                    .toList();

            return Optional.of(bestBuyers);
        } catch (Exception e) {
            log.error("Error while generating top buyers report: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<MonthSalesReportModel> getMonthlyReport(Integer month) {
        log.info("Generating monthly sales report for month: {}", month);

        try {
            List<Order> paidOrders = ordersRepository.findByStatusAndMonth(
                    "PAID", month, LocalDateTime.now().getYear());

            Double amountTotalSales = paidOrders.stream()
                    .mapToDouble(Order::getTotalValue)
                    .sum();

            Integer totalOrders = paidOrders.size();

            Double averagePerOrder = totalOrders > 0
                    ? amountTotalSales / totalOrders
                    : 0;

            return Optional.of(new MonthSalesReportModel(amountTotalSales, totalOrders, averagePerOrder));
        } catch (Exception e) {
            log.error("Error while generating monthly sales report: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }
}
