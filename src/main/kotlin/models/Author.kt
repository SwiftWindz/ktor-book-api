package com.example.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

@Serializable
data class Author(
    val firstName: String,
    val lastName: String,
) {
    companion object {
        private val json = Json { ignoreUnknownKeys = true }
        fun fromDocument(document: Document): Author = json.decodeFromString(document.toJson())
    }

    fun toDocument(): Document = Document.parse(Json.encodeToString(this))
}