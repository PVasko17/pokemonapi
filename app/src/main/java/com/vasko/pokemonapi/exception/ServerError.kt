package com.vasko.pokemonapi.exception

/**
 * Exception class to use on server errors
 */
class ServerError : RuntimeException {

    val errorBody: String

    constructor(message: String) : super(message) {
        errorBody = ""
    }

    constructor(message: String, errorBody: String) : super(message) {
        this.errorBody = errorBody
    }
}
