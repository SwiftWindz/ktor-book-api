package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRoutingBase() {
    routing {
        swaggerUI(path = "api-docs", swaggerFile = "openapi/documentation.yaml")
        get("/") {
            call.respondText("Book Central")
        }
    }
}
