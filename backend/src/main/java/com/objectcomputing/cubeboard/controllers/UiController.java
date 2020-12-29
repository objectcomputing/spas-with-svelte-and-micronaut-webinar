package com.objectcomputing.cubeboard.controllers;

import com.objectcomputing.cubeboard.conf.AppConfiguration;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller("/ui")
public class UiController {
    private final AppConfiguration appConfiguration;

    public UiController(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    @Operation(operationId = "ui",
            summary = "redirects to the ui application",
            description = "Redirects to ui service",
            responses = {
                    @ApiResponse(responseCode = "303", description = "redirects to UI service"),
            })
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get
    public HttpResponse<?> index() {
        return HttpResponse.seeOther(UriBuilder.of(appConfiguration.getLoginRedirect()).build());
    }
}
