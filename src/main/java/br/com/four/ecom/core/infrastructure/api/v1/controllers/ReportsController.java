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
        // Implement the logic to generate the report based on the request
        return new ReportResponse(averageTicketReport.execute(reportRequest.toInput()));
    }

    @Override
    @PostMapping("/report/monthly")
    public ReportResponse monthlyReport(@RequestBody ReportRequest reportRequest) {
        // Implement the logic to generate the monthly report based on the request
        return new ReportResponse(monthlyReport.execute(reportRequest.toInput()));
    }

    @Override
    @GetMapping("/report/best-buyers/{bestBuyersCount}")
    public ReportResponse bestBuyersReport(@PathVariable Integer bestBuyersCount) {
        // Implement the logic to generate the best buyers report based on the count
        return new ReportResponse(bestBuyersReport.execute(bestBuyersCount));
    }
}
