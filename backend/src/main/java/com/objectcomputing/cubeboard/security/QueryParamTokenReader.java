package com.objectcomputing.cubeboard.security;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.token.reader.TokenReader;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class QueryParamTokenReader implements TokenReader {

    public static final String TOKEN = "token";

    @Override
    public Optional<String> findToken(HttpRequest<?> request) {
        return Optional.ofNullable(request.getParameters().get(TOKEN));
    }
}
