package com.objectcomputing.cubeboard.controllers;

import com.objectcomputing.cubeboard.Application;
import com.objectcomputing.cubeboard.Dashboard;
import com.objectcomputing.cubeboard.DashboardFetcher;
import com.objectcomputing.cubeboard.DashboardPage;
import com.objectcomputing.cubeboard.versioning.ApiVersion;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@Controller("/api")
public class DashboardsController {

    private final DashboardFetcher dashboardFetcher;

    public DashboardsController(DashboardFetcher dashboardFetcher) {
        this.dashboardFetcher = dashboardFetcher;
    }

    @Operation(operationId = "api-dashboards",
            summary = "list of dashboards",
            description = "Return a list of dashboards",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,
                            name = "X-API-Version",
                            description = "API version number",
                            example = "1.0"),
            },
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "dashboards information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DashboardPage.class)
                            )
                    ),
            }
    )
    @SecurityRequirement(name = Application.COOKIE_AUTH)
    @Version(ApiVersion.V1)
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/dashboards")
    public DashboardPage index() {
        List<Dashboard> dashboardList = dashboardFetcher.findAll();
        DashboardPage page = new DashboardPage();
        page.setCount(dashboardList.size());
        page.setData(dashboardList);
        return page;
    }
}
