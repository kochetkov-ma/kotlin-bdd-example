package org.brewcode.bdd.kotest

import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import org.brewcode.bdd.RequestDto
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ValidationControllerBddKotestTest(@Value("\${local.server.port}") private val localServerPort: String) :
    FreeSpec() {

    override fun extensions() = listOf(SpringExtension)

    private lateinit var body: RequestSpecification
    private lateinit var post: Response

    init {
        "Scenario: Отсутствует текст в исходном запросе [@REQ=1]" - {

            "Подготовка" - {

                "Given настроен стенд и готов принимать запросы" {}

                "And подготовлен POST запрос с отсутствующим полем text" {
                    body = RestAssured
                        .with()
                        .log()
                        .all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .body(RequestDto(null))
                }
            }

            "Действие" - {

                "When отправлен запрос" {
                    post = body.post("http://localhost:$localServerPort/validation")
                }

            }

            "Результат" - {

                "Then получен ответ с кодом ошибки 1 и сообщением 'Null text field'" {
                    post
                        .then()
                        .log()
                        .all()
                        .statusCode(200)
                        .body("code", Matchers.equalTo(1))
                        .body("message", Matchers.equalTo("Null text field"))
                }
            }
        }
    }
}