package org.beauty.auth.mock

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class CodeGenerationService {
    private val codes = HashMap<String, String>()

    fun generateCode(phoneNumber: String): String {
        codes[phoneNumber] = Random.nextInt(8999).plus(1000).toString()
        return codes[phoneNumber]!!
    }

    fun getCode(phoneNumber: String): String? = codes[phoneNumber]

}