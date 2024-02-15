package org.beauty.auth.mock

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api/mock/code")
class CodeGenerationController (private val codeGenerationService: CodeGenerationService){
    @PostMapping("/generate/{phoneNumber}")
    fun generateCode(@PathVariable phoneNumber: String) :String =
        codeGenerationService.generateCode(phoneNumber)

    @GetMapping("/get/{phoneNumber}")
    fun getCode(@PathVariable phoneNumber: String): String =
        codeGenerationService.getCode(phoneNumber)
            ?:throw ResponseStatusException(HttpStatus.NOT_FOUND)
}