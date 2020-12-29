package com.objectcomputing.cubeboard.controllers;

import com.objectcomputing.cubeboard.Application;
import com.objectcomputing.cubeboard.NoSuchLabelException;
import com.objectcomputing.cubeboard.Value;
import com.objectcomputing.cubeboard.ValuePage;
import com.objectcomputing.cubeboard.ValuesFetcher;
import com.objectcomputing.cubeboard.versioning.ApiVersion;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpResponse;
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

import java.util.List;

@Controller("/api")
public class ValuesController {

    private final ValuesFetcher valuesFetcher;

    public ValuesController(ValuesFetcher valuesFetcher) {
        this.valuesFetcher = valuesFetcher;
    }

    @Operation(operationId = "api-values",
            summary = "Slot's values",
            description = "Return the slot's values identified by a label",
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
                            description = "if slot found for the specified value"),
                    @ApiResponse(responseCode = "200",
                            description = "A dataset detail",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValuePage.class)
                            )
                    ),
            }
    )
    @SecurityRequirement(name = Application.COOKIE_AUTH)
    @Version(ApiVersion.V1)
    @Get("/values/{label}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<?> index(@PathVariable String label) {
        try {
            List<Value> values = valuesFetcher.findByLabel(label);
            ValuePage page = new ValuePage();
            page.setCount(values.size());
            page.setData(values);
            return HttpResponse.ok(page);
        } catch (NoSuchLabelException e) {
            e.printStackTrace();
            return HttpResponse.notFound();
        }
    }
}
