package com.objectcomputing.cubeboard.controllers;

import com.objectcomputing.cubeboard.Application;
import com.objectcomputing.cubeboard.Dashboard;
import com.objectcomputing.cubeboard.DashboardFetcher;
import com.objectcomputing.cubeboard.versioning.ApiVersion;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.Optional;

@Controller("/api")
public class DashboardController {

    private final DashboardFetcher dashboardFetcher;

    public DashboardController(DashboardFetcher dashboardFetcher) {
        this.dashboardFetcher = dashboardFetcher;
    }

    @Operation(operationId = "api-dashboard-slug",
            summary = "dashboard's detail",
            description = "Return dashboard's information given a dashboard's slug",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,
                            name = "X-API-Version",
                            description = "API version number",
                            example = "1.0"),
                    @Parameter(in = ParameterIn.PATH,
                            name = "label",
                            required = true,
                            examples = {
                                    @ExampleObject(name = "label", value = "55"),
                            },
                            description = "slot's label"
                    ),
            },
            responses = {
                    @ApiResponse(responseCode = "400",
                            description = "if dashboard found for the specified slot"),
                    @ApiResponse(responseCode = "200",
                            description = "dashboard information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Dashboard.class)
                            )
                    ),
            }
    )
    @SecurityRequirement(name = Application.COOKIE_AUTH)
    @Version(ApiVersion.V1)
    @Get("/dashboards/{slug}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public Optional<Dashboard> index(@PathVariable String slug) {
        return dashboardFetcher.findBySlug(slug);
    }
}
