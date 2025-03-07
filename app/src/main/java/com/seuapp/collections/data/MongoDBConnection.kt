package com.seuapp.collections.data

import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import org.bson.Document

class MongoDBConnection {

    private val client = MongoClients.create("mongodb+srv://fitznieri:a42dqVF7g1kK41qU@collectionsdb.gan4y.mongodb.net/")
    private val database = client.getDatabase("CollectionsDB")

    fun getCollection(collectionName: String): MongoCollection<Document>? {
        return try {
            database.getCollection(collectionName)
        } catch (e: Exception) {
            println("Erro ao acessar a coleção: ${e.message}")
            null
        }
    }

    fun close() {
        try {
            client.close()
        } catch (e: Exception) {
            println("Erro ao fechar a conexão: ${e.message}")
        }
    }
}