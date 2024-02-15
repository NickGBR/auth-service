package org.beauty.auth.controller.user

import org.beauty.auth.enums.Role
import org.beauty.auth.model.User
import org.beauty.auth.service.impl.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/user")
class UserController (
    private val service: UserService,
){

    @GetMapping("/exists/{phoneNumber}")
    fun checkExistence(@PathVariable phoneNumber: String) : ResponseEntity<Any>{
        return if(service.userExists(phoneNumber)){
            ResponseEntity(HttpStatus.OK)
        } else{
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/create")
    fun create(@RequestBody userRequest: CreateUserRequest): UserResponse =
        service.createUser(userRequest.toModel(), userRequest.code)
            ?.toUserResponse()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create a user")

    @QueryMapping
    fun getUserByPhoneNumber(@Argument phoneNumber: String) : UserResponse? {
        return service.findByPhoneNumber(phoneNumber)?.toUserResponse()
    }


    private fun CreateUserRequest.toModel() : User =
        User(
            name = this.name,
            surname = this.surname,
            phoneNumber = this.phoneNumber,
            password = this.password,
            role = Role.USER
        )

    private fun User.toUserResponse() : UserResponse =
        UserResponse(
            name = this.name,
            surname = this.surname,
            phoneNumber = this.phoneNumber,
        )

}