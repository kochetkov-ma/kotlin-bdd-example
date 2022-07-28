package org.brewcode.bdd

import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ValidationController {

    @PostMapping("/validation", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun sampleValidateEndpoint(@RequestBody request: RequestDto): ResponseDto =
        when {
            request.text == null -> ResponseDto(1, "Null text field")
            request.text.isBlank() -> ResponseDto(2, "Blank text field")
            else -> ResponseDto(0, "Ok")
        }
}