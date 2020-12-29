package com.objectcomputing.cubeboard.controllers

import com.objectcomputing.cubeboard.EmbeddedServerSpecification
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

class HomeControllerSpec extends EmbeddedServerSpecification {

    @Override
    Map<String, Object> getConfiguration() {
        super.configuration + ['micronaut.http.client.followRedirects': false]
    }

    void "/ redirects to /swagger-ui"() {
        when:
        HttpResponse rsp = client.exchange(HttpRequest.GET('/'))
        then:
        noExceptionThrown()
        rsp.status() == HttpStatus.SEE_OTHER
    }
}
