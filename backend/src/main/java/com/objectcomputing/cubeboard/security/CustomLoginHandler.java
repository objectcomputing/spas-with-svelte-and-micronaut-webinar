package com.objectcomputing.cubeboard.security;

import com.objectcomputing.cubeboard.conf.AppConfiguration;
import io.micronaut.context.annotation.Primary;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.handlers.RedirectingLoginHandler;
import io.micronaut.security.oauth2.endpoint.token.response.OpenIdUserDetailsMapper;
import io.micronaut.security.token.config.TokenConfiguration;

import javax.inject.Singleton;
import java.net.URI;
import java.util.Map;

@Primary
@Singleton
public class CustomLoginHandler implements RedirectingLoginHandler {

    private final AppConfiguration appConfiguration;
    private final TokenConfiguration tokenConfiguration;

    public CustomLoginHandler(AppConfiguration appConfiguration, TokenConfiguration tokenConfiguration) {
        this.appConfiguration = appConfiguration;
        this.tokenConfiguration = tokenConfiguration;
    }

    @Override
    public MutableHttpResponse<?> loginSuccess(UserDetails userDetails, HttpRequest<?> request) {
        Map<String, Object> attributes = userDetails.getAttributes(tokenConfiguration.getRolesName(), tokenConfiguration.getNameKey());
        if (attributes.containsKey(OpenIdUserDetailsMapper.OPENID_TOKEN_KEY)) {
            URI uri = UriBuilder.of(appConfiguration.getLoginRedirect())
                    .queryParam(appConfiguration.getParamToken(), attributes.get(OpenIdUserDetailsMapper.OPENID_TOKEN_KEY))
                    .build();
            return HttpResponse.seeOther(uri);
        }
        return HttpResponse.seeOther(URI.create(LoginFailedController.PATH));
    }

    @Override
    public MutableHttpResponse<?> loginRefresh(UserDetails userDetails, String refreshToken, HttpRequest<?> request) {
        return HttpResponse.seeOther(URI.create(LoginFailedController.PATH));
    }

    @Override
    public MutableHttpResponse<?> loginFailed(AuthenticationResponse authenticationResponse, HttpRequest<?> request) {
        return HttpResponse.seeOther(URI.create(LoginFailedController.PATH));
    }
}
