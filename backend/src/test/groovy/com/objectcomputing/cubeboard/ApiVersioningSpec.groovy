package com.objectcomputing.cubeboard

class ApiVersioningSpec extends ApplicationContextSpecification {

    void "api versioning is enabled"() {
        expect:
        applicationContext.getProperty("micronaut.router.versioning.enabled", Boolean).isPresent()
        applicationContext.getProperty("micronaut.router.versioning.enabled", Boolean).get()
    }
}
