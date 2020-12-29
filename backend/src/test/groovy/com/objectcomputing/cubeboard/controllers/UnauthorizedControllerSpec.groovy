package com.objectcomputing.cubeboard.controllers

import com.objectcomputing.cubeboard.EmbeddedServerSpecification
import io.micronaut.http.HttpRequest

class UnauthorizedControllerSpec extends EmbeddedServerSpecification {

    void "/unauthorized is not secured"() {
        when:
        String html = client.retrieve(HttpRequest.GET("/unauthorized"), String)

        then:
        noExceptionThrown()
        html == "<!DOCTYPE html><html><head><title>Unauthorized</title></head><body><h1>Unauthorized</h1></body></html>"
    }
}
