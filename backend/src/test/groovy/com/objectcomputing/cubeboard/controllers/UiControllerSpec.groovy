package com.objectcomputing.cubeboard.controllers

import com.objectcomputing.cubeboard.EmbeddedServerSpecification
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

class UiControllerSpec extends EmbeddedServerSpecification {

    @Override
    Map<String, Object> getConfiguration() {
        super.configuration + ['micronaut.http.client.followRedirects': false]
    }

    void "/ui redirects to the ui service"() {
        when:
        HttpResponse rsp = client.exchange(HttpRequest.GET('/ui'))
        then:
        noExceptionThrown()
        rsp.status() == HttpStatus.SEE_OTHER
    }
}
