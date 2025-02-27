package com.example.routes.misc

import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureSwagger() {
    routing {
        swaggerUI(path = "api-docs", swaggerFile = "openapi/documentation.yaml")
    }
}
