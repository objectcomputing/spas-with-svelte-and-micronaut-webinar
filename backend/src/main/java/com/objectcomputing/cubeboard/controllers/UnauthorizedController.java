package com.objectcomputing.cubeboard.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller("/unauthorized")
public class UnauthorizedController {
    public static final String UNAUTHORIZED_HTML = "<!DOCTYPE html><html><head><title>Unauthorized</title></head><body><h1>Unauthorized</h1></body></html>";

    @Operation(operationId = "unauthorized",
            summary = "User access unauthorized",
            description = "Renders an HTML page telling the user that access is unauthorized",
            responses = { @ApiResponse(responseCode = "200") })
    @Get
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Produces(MediaType.TEXT_HTML)
    public String index() {
        return UNAUTHORIZED_HTML;
    }
}
