package org.brewcode.bdd.step

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import org.brewcode.bdd.RequestDto
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType

class ValidationControllerStep {
    private lateinit var body: RequestSpecification
    private lateinit var post: Response

    @Value("\${local.server.port}")
    private val localServerPort = 0

    @Given("настроен стенд и готов принимать запросы")
    fun настроенСтендИГотовПриниматьЗапросы() {

    }

    @And("подготовлен POST запрос с отсутствующим полем text")
    fun подготовленPOSTЗапросСОтсутствующимПолемText() {
        body = RestAssured
            .with()
            .log()
            .all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .body(RequestDto(null))
    }

    @When("отправлен запрос")
    fun отправленЗапрос() {
        post = body.post("http://localhost:$localServerPort/validation")
    }

    @Then("получен ответ с кодом ошибки {int} и сообщением {string}")
    fun полученОтветСКодомОшибкиИСообщениемNullTextField(code: Int, message: String) {
        post
            .then()
            .log()
            .all()
            .statusCode(200)
            .body("code", Matchers.equalTo(1))
            .body("message", Matchers.equalTo("Null text field"))
    }
}