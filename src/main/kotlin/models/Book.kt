package com.example.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.bson.Document

@Serializable
data class Book(
    val isbn: Int,
    val authorID: String,
    val title: String,
    val edition: Int
) {
    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): Book = json.decodeFromString(document.toJson())
    }

    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

}
