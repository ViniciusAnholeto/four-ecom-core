package br.com.four.ecom.core.infrastructure.api.v1.controllers;

import br.com.four.ecom.core.domains.reports.resources.AverageTicketReport;
import br.com.four.ecom.core.domains.reports.resources.BestBuyersReport;
import br.com.four.ecom.core.domains.reports.resources.MonthlyReport;
import br.com.four.ecom.core.infrastructure.api.v1.request.ReportRequest;
import br.com.four.ecom.core.infrastructure.api.v1.response.ReportResponse;
import br.com.four.ecom.core.infrastructure.api.v1.swaggers.ReportsControllerDoc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ecom/v1")
public class ReportsController implements ReportsControllerDoc {

    private AverageTicketReport averageTicketReport;
    private BestBuyersReport bestBuyersReport;
    private MonthlyReport monthlyReport;

    @Override
    @PostMapping("/report")
    public ReportResponse averageTicketReport(@RequestBody ReportRequest reportRequest) {

        log.info("Generating average ticket report for user id: {}", reportRequest.getUserId());

        return new ReportResponse(averageTicketReport.execute(reportRequest.toInput()));
    }

    @Override
    @GetMapping("/report/monthly")
    public ReportResponse monthlyReport() {

        log.info("Generating monthly report for month: {}", LocalDateTime.now().getMonth().name());

        return new ReportResponse(monthlyReport.execute());
    }

    @Override
    @PostMapping("/report/best-buyers/{bestBuyersCount}")
    public ReportResponse bestBuyersReport(@PathVariable Integer bestBuyersCount,
                                           @RequestBody ReportRequest reportRequest) {

        return new ReportResponse(bestBuyersReport.execute(bestBuyersCount, reportRequest.toInput()));
    }
}
