package com.objectcomputing.cubeboard.controllers

import com.objectcomputing.cubeboard.EmbeddedServerSpecification
import io.micronaut.http.HttpRequest

class DeniedControllerSpec extends EmbeddedServerSpecification {

    void "/denied is not secured"() {
        when:
        String html = client.retrieve(HttpRequest.GET("/denied"), String)

        then:
        noExceptionThrown()
        html == "<!DOCTYPE html><html><head><title>Denied</title></head><body><h1>Denied</h1></body></html>"
    }
}
