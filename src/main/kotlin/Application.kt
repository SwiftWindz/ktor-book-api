package com.example

import com.example.routes.configureRoutingBase
import com.example.routes.configureRoutingBooks
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}


fun Application.module() {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
    configureRoutingBase()
    configureRoutingBooks()
}
