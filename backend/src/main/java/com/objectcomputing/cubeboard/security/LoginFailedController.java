package com.objectcomputing.cubeboard.security;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller(LoginFailedController.PATH)
public class LoginFailedController {
    public static final String PATH = "/loginfailed";

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Produces(MediaType.TEXT_HTML)
    @Get
    public String index() {
        return "<!DOCTYPE html><html><head><title>Page Title</title></head><body><h1>Login failed</h1></body></html>";
    }
}
