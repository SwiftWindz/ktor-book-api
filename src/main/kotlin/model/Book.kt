package com.example.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.bson.Document

@Serializable
data class Book(
    val isbn: Int,
    val title: String,
    val author: String,
    val edition: Int
) {
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): Book = json.decodeFromString(document.toJson())
    }
}
