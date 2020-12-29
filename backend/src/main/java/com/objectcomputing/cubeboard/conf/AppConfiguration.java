package com.objectcomputing.cubeboard.conf;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("app")
public class AppConfiguration {

    @NotBlank
    @NonNull
    private String loginRedirect;

    @NotBlank
    @NonNull
    private String paramToken;

    @NonNull
    public String getParamToken() {
        return paramToken;
    }

    public void setParamToken(@NonNull String paramToken) {
        this.paramToken = paramToken;
    }

    @NonNull
    public String getLoginRedirect() {
        return loginRedirect;
    }

    public void setLoginRedirect(@NonNull String loginRedirect) {
        this.loginRedirect = loginRedirect;
    }
}
