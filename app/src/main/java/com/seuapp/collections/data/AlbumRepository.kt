package com.seuapp.collections.data

import Album
import org.bson.Document
import com.mongodb.MongoException

class AlbumRepository(private val mongoDBConnection: MongoDBConnection) {

    private val collection = mongoDBConnection.getCollection("albums")

    // Obter todos os álbuns
    fun getAllAlbums(): List<Album> {
        val albums = mutableListOf<Album>()
        try {
            val cursor = collection?.find()?.iterator()
            if (cursor != null) {
                while (cursor.hasNext()) {
                    val document = cursor.next()
                    albums.add(
                        Album(
                            title = document["title"].toString(),
                            coverUrl = document["coverUrl"].toString(),
                            artist = document["artist"].toString(),
                            year = document["year"] as Int,
                            owned = document["owned"] as Boolean
                        )
                    )
                }
            }
        } catch (e: MongoException) {
            // Handle exception (e.g., log error)
            println("Error fetching albums: ${e.message}")
        }
        return albums
    }

    // Inserir um novo álbum
    suspend fun insertAlbum(album: Album) {
        try {
            val document = Document("title", album.title)
                .append("coverUrl", album.coverUrl)
                .append("artist", album.artist)
                .append("year", album.year)
                .append("owned", album.owned)
            collection?.insertOne(document)
        } catch (e: MongoException) {
            // Handle exception (e.g., log error)
            println("Error inserting album: ${e.message}")
        }
    }

    // Excluir um álbum
    suspend fun deleteAlbum(title: String) {
        try {
            val query = Document("title", title)
            collection?.deleteOne(query)
        } catch (e: MongoException) {
            // Handle exception (e.g., log error)
            println("Error deleting album: ${e.message}")
        }
    }

    // Alternar o status de 'owned' de um álbum
    suspend fun toggleOwned(title: String) {
        try {
            val query = Document("title", title)
            val update = Document("\$bit", Document("owned", 1)) // Alterna booleano no MongoDB
            collection?.updateOne(query, update)
        } catch (e: MongoException) {
            println("Error toggling owned status: ${e.message}")
        }
    }

    // Atualizar álbum por título
    suspend fun updateAlbumByTitle(
        title: String,
        newArtist: String? = null,
        newYear: Int? = null,
        newCoverUrl: String? = null
    ) {
        try {
            val query = Document("title", title)
            val updateFields = mutableMapOf<String, Any>()

            newArtist?.let { updateFields["artist"] = it }
            newYear?.let { updateFields["year"] = it }
            newCoverUrl?.let { updateFields["coverUrl"] = it }

            if (updateFields.isNotEmpty()) {
                val updateDocument = Document("\$set", Document(updateFields))
                collection?.updateOne(query, updateDocument)
            }
        } catch (e: MongoException) {
            println("Error updating album: ${e.message}")
        }
    }
}