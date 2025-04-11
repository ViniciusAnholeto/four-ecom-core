package br.com.four.ecom.core.infrastructure.api.v1.controllers;

import br.com.four.ecom.core.domains.reports.resources.AverageTicketReport;
import br.com.four.ecom.core.domains.reports.resources.BestBuyersReport;
import br.com.four.ecom.core.domains.reports.resources.MonthlyReport;
import br.com.four.ecom.core.infrastructure.api.v1.request.DataRangeRequest;
import br.com.four.ecom.core.infrastructure.api.v1.request.ReportRequest;
import br.com.four.ecom.core.infrastructure.api.v1.response.ReportResponse;
import br.com.four.ecom.core.infrastructure.api.v1.swaggers.ReportsControllerDoc;
import br.com.four.ecom.core.infrastructure.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ecom/v1")
public class ReportsController implements ReportsControllerDoc {

    private final AverageTicketReport averageTicketReport;
    private final BestBuyersReport bestBuyersReport;
    private final MonthlyReport monthlyReport;
    private final AuthService authorizationService;

    private static final String BEARER = "Bearer ";
    private static final String ADMIN = "ADMIN";

    @Override
    @PostMapping("/report")
    public ReportResponse averageTicketReport(@RequestHeader("Authorization") String authorizationHeader,
                                              @RequestBody ReportRequest reportRequest) {

        String token = authorizationHeader.replace(BEARER, "");
        authorizationService.validateRole(token, ADMIN, "averageTicketReport");

        log.info("Generating average ticket report for user id: {}", reportRequest.getCustomerId());

        return new ReportResponse(averageTicketReport.execute(reportRequest.toInput()));
    }

    @Override
    @GetMapping("/report/monthly")
    public ReportResponse monthlyReport(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.replace(BEARER, "");
        authorizationService.validateRole(token, ADMIN, "monthlyReport");

        log.info("Generating monthly report for month: {}", LocalDateTime.now().getMonth().name());

        return new ReportResponse(monthlyReport.execute());
    }

    @Override
    @PostMapping("/report/best-buyers/{bestBuyersCount}")
    public ReportResponse bestBuyersReport(@RequestHeader("Authorization") String authorizationHeader,
                                           @PathVariable Integer bestBuyersCount,
                                           @RequestBody DataRangeRequest dataRange) {

        String token = authorizationHeader.replace(BEARER, "");
        authorizationService.validateRole(token, ADMIN, "bestBuyersReport");

        return new ReportResponse(bestBuyersReport.execute(bestBuyersCount, dataRange.toInput()));
    }
}
