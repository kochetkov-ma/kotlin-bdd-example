package org.brewcode.bdd.unit

import io.restassured.RestAssured
import org.brewcode.bdd.RequestDto
import org.hamcrest.Matchers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ValidationControllerClassicTest {

    @Value("\${local.server.port}")
    private val localServerPort = 0

    @Test
    @DisplayName("@REQ=1 > E2E тестирование POST метода - требование 1")
    fun testSampleGetEndpointTextNull() {
        RestAssured
            .with()
            .log()
            .all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .body(RequestDto(null))
            .post("http://localhost:$localServerPort/validation")
            .then()
            .log()
            .all()
            .statusCode(200)
            .body("code", Matchers.equalTo(1))
            .body("message", Matchers.equalTo("Null text field"))
    }
}