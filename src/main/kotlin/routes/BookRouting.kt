package com.example.routes

import com.example.model.Book
import com.example.db.connectToMongoDB
import com.example.service.BookService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRoutingBooks() {
    val mongoDatabase = connectToMongoDB()
    val bookService = BookService(mongoDatabase)
    routing {
        // Create book
        post("/books") {
            val book = call.receive<Book>()
            val id = bookService.create(book)
            call.respond(HttpStatusCode.Created, id)
        }
        // Read book
        get("/books/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("No ID found")
            bookService.read(id)?.let { book ->
                call.respond(book)
            } ?: call.respond(HttpStatusCode.NotFound)
        }
        // Update book
        put("/books/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("No ID found")
            val book = call.receive<Book>()
            bookService.update(id, book)?.let {
                call.respond(HttpStatusCode.OK)
            } ?: call.respond(HttpStatusCode.NotFound)
        }
        // Delete book
        delete("/books/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("No ID found")
            bookService.delete(id)?.let {
                call.respond(HttpStatusCode.OK)
            } ?: call.respond(HttpStatusCode.NotFound)
        }
    }
}