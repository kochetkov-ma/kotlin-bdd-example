package org.brewcode.bdd.unit;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.brewcode.bdd.RequestDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidationControllerJunitBddTest {

    @Value("${local.server.port}")
    private int localServerPort = 0;

    private RequestSpecification body;
    private Response post;

    @Test
    @DisplayName("Scenario: Отсутствует текст в исходном запросе [@REQ=1]")
    void testSampleGetEndpointTextNull() {

        step("Подготовка", () -> {
            step("Given настроен стенд и готов принимать запросы", () -> {
                assertThat(localServerPort).isNotZero();
            });
            step("Given настроен стенд и готов принимать запросы", () -> {
                body = RestAssured
                        .with()
                        .log()
                        .all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .body(new RequestDto(null));
            });
        });

        step("Действие", () -> {
            step("When отправлен запрос", () -> {
                post = body.post("http://localhost:%s/validation".formatted(localServerPort));
            });
        });


        step("Результат", () -> {
            step("Then получен ответ с кодом ошибки 1 и сообщением 'Null text field'", () -> {
                post
                        .then()
                        .log()
                        .all()
                        .statusCode(200)
                        .body("code", Matchers.equalTo(1))
                        .body("message", Matchers.equalTo("Null text field"));
            });
        });
    }
}
