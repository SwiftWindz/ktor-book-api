package com.example.service

import com.example.model.Book
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import org.bson.types.ObjectId

class BookService(private val database: MongoDatabase) {
    var collection: MongoCollection<Document>

    init {
        database.createCollection("book")
        collection = database.getCollection("book")
    }

    // Create new Book
    suspend fun create(book: Book): String = withContext(Dispatchers.IO) {
        val doc = book.toDocument()
        collection.insertOne(doc)
        doc["_id"].toString()
    }

    // Read a Book
    suspend fun read(id: String): Book? = withContext(Dispatchers.IO) {
        collection.find(Filters.eq("_id", ObjectId(id))).first()?.let(Book.Companion::fromDocument)
    }

    // Update a Book
    suspend fun update(id: String, book: Book): Document? = withContext(Dispatchers.IO) {
        collection.findOneAndReplace(Filters.eq("_id", ObjectId(id)), book.toDocument())
    }

    // Delete a Book
    suspend fun delete(id: String): Document? = withContext(Dispatchers.IO) {
        collection.findOneAndDelete(Filters.eq("_id", ObjectId(id)))
    }
}