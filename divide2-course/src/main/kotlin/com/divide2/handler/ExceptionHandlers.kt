package com.divide2.handler

import com.divide2.core.exception.ValidationException
import com.divide2.exception.BadAuthenticationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class Errors(
        var message: String,
        var errors: List<Error>?
)

data class Error(
        var resource: String,
        var field: String,
        var code: String
)

/**
 * @author bvvy
 * @date 2019/10/15
 */
@RestControllerAdvice
class ExceptionHandlers {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun message(e: Exception): ResponseEntity<Errors> {
        val errors = Errors("Body should be a JSON object", null)
        return ResponseEntity.badRequest().body(errors)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun notValid(e: MethodArgumentNotValidException): ResponseEntity<Errors> {
        val bindingResult = e.bindingResult
        val errorList = bindingResult.allErrors.map {
            when (it) {
                is FieldError -> Error(it.objectName, it.field, it.code ?: "none")
                else -> Error(it.objectName, "none", it.code ?: "none")
            }
        }
        val errors = Errors("Validation Failed", errorList)
        return ResponseEntity.unprocessableEntity().body(errors)
    }

    @ExceptionHandler(BadAuthenticationException::class)
    fun notValid(e: BadAuthenticationException): ResponseEntity<Errors> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Errors(e.message!!, null))
    }

    @ExceptionHandler(ValidationException::class)
    fun notValid(e: ValidationException): ResponseEntity<Errors> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Errors(e.message!!, null))
    }
}
