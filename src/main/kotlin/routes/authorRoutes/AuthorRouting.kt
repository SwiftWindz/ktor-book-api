package com.example.routes.authorRoutes

import com.example.models.Author
import com.example.db.connectToMongoDB
import com.example.service.AuthorService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRoutingAuthors() {
    val mongoDatabase = connectToMongoDB()
    val authorService = AuthorService(mongoDatabase)
    routing {
        // Create author
        post("/author") {
            val author = call.receive<Author>()
            val id = authorService.create(author)
            call.respond(HttpStatusCode.Created, id)
        }
        // Read author
        get("/author/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("No ID found")
            authorService.read(id)?.let { author ->
                call.respond(author)
            } ?: call.respond(HttpStatusCode.NotFound)
        }
        // Update author
        put("/author/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("No ID found")
            val author = call.receive<Author>()
            authorService.update(id, author)?.let {
                call.respond(HttpStatusCode.OK)
            } ?: call.respond(HttpStatusCode.NotFound)
        }
        // Delete author
        delete("/author/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("No ID found")
            authorService.delete(id)?.let {
                call.respond(HttpStatusCode.OK)
            } ?: call.respond(HttpStatusCode.NotFound)
        }
    }
}