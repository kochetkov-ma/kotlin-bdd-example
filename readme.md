BDD подход при написании тестов на Kotlin. `Junit5` / `Cucumber` / `Kotest`
---

## BDD и BDT презентация
[BDD.pptx](pptx/BDD.pptx)
[Требования](pptx/requirements.md)

## Spring Boot
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'com.fasterxml.jackson.module:jackson-module-kotlin'

    // Sprint Boot Test
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'io.rest-assured:rest-assured'
}
```
[ValidationController](src/main/kotlin/org/brewcode/bdd/ValidationController.kt) с одним POST методом по пути `/validation`

## `JUnit5` и Spring Boot Test
```groovy
dependencies {
    // JUnit5
    testImplementation('org.junit.jupiter:junit-jupiter')
    testRuntimeOnly('org.junit.jupiter:junit-jupiter-engine')
    testImplementation('org.junit.platform:junit-platform-suite')
}
```
[Классический тест](src/test/kotlin/org/brewcode/bdd/unit/ValidationControllerClassicTest.kt)

## `Cucumber 7` и Spring Boot Test
```groovy
dependencies {
    // Cucumber
    testImplementation 'io.cucumber:cucumber-java:7.4.1'
    testImplementation 'io.cucumber:cucumber-junit-platform-engine:7.4.1'
    testImplementation 'io.cucumber:cucumber-spring:7.4.1'
}
```
- [Cucumber Runner](src/test/kotlin/org/brewcode/bdd/cucumber/ValidationControllerBddCucumberTest.kt)
- [Cucumber Feature](src/test/resources/features/validation.feature)
- [Cucumber Steps](src/test/kotlin/org/brewcode/bdd/step/ValidationControllerStep.kt)
- [Cucumber Spring Configuration](src/test/kotlin/org/brewcode/bdd/step/CucumberSpringConfiguration.kt)

## `Kotest 5` и Spring Boot Test
```groovy
dependencies {
    // Kotest
    testImplementation 'io.kotest:kotest-runner-junit5:5.3.2'
    testImplementation 'io.kotest.extensions:kotest-extensions-spring:1.1.1'
}
```
[Kotest](src/test/kotlin/org/brewcode/bdd/kotest/ValidationControllerBddKotestTest.kt)