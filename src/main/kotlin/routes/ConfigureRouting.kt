package com.example.routes

import com.example.routes.authorRoutes.*
import com.example.routes.bookRoutes.*
import com.example.routes.misc.*
import io.ktor.server.application.*

fun Application.configureApplicationRouting() {
    configureRoutingBase()
    configureSwagger()
    configureRoutingBooks()
    configureRoutingAuthors()
}