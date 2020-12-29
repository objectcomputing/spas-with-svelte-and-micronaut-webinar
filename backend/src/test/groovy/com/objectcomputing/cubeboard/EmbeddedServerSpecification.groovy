package com.objectcomputing.cubeboard

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

abstract class EmbeddedServerSpecification extends Specification implements ConfigurationFixture {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, configuration, Environment.TEST)

    @Shared
    @AutoCleanup
    ApplicationContext applicationContext = embeddedServer.applicationContext

    @Shared
    @AutoCleanup
    HttpClient httpClient = applicationContext.createBean(HttpClient, embeddedServer.URL)

    @Shared
    @AutoCleanup
    BlockingHttpClient client = httpClient.toBlocking()
}
