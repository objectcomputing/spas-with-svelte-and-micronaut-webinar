package com.objectcomputing.cubeboard.controllers;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.net.URI;

@Controller
public class HomeController {
    @Operation(operationId = "home",
            summary = "Root Path",
            description = "Redirects to Swagger UI page",
            responses = {
                    @ApiResponse(responseCode = "303", description = "redirects to Swagger UI page Screen"),
            })
    @Get
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<?> index() {
        return HttpResponse.seeOther(URI.create("/swagger-ui/"));
    }
}