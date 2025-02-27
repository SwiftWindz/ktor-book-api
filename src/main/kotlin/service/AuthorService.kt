package com.example.service

import com.example.models.Author
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import org.bson.types.ObjectId

class AuthorService(private val database: MongoDatabase) {
    private var collection: MongoCollection<Document>

    init {
        database.createCollection("authors")
        collection = database.getCollection("authors")
    }

    // Create new author
    suspend fun create(author: Author): String = withContext(Dispatchers.IO) {
        val doc = author.toDocument()
        collection.insertOne(doc)
        doc["_id"].toString()
    }

    // Read an author
    suspend fun read(id: String): Author? = withContext(Dispatchers.IO) {
        collection.find(Filters.eq("_id", ObjectId(id))).first()?.let(Author.Companion::fromDocument)
    }

    // Update a author
    suspend fun update(id: String, author: Author): Document? = withContext(Dispatchers.IO) {
        collection.findOneAndReplace(Filters.eq("_id", ObjectId(id)), author.toDocument())
    }

    // Delete a author
    suspend fun delete(id: String): Document? = withContext(Dispatchers.IO) {
        collection.findOneAndDelete(Filters.eq("_id", ObjectId(id)))
    }
}