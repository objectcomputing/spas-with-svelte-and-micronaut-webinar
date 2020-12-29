package com.objectcomputing.cubeboard.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller("/denied")
public class DeniedController {
    public static final String DENIED_HTML = "<!DOCTYPE html><html><head><title>Denied</title></head><body><h1>Denied</h1></body></html>";

    @Operation(operationId = "denied",
            summary = "User access denied",
            description = "Renders an HTML page telling the user that access is denied",
            responses = { @ApiResponse(responseCode = "200") })
    @Get
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Produces(MediaType.TEXT_HTML)
    public String index() {
        return DENIED_HTML;
    }
}
