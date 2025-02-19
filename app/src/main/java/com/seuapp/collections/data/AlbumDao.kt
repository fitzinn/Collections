package com.seuapp.collections.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {

    @Insert
    suspend fun insert(album: Album)

    @Delete
    suspend fun delete(album: Album)

    @Query("SELECT * FROM albums WHERE title = :title LIMIT 1")
    suspend fun getAlbumByTitle(title: String): Album?

    @Query("UPDATE albums SET owned = NOT owned WHERE title = :title")
    suspend fun toggleOwned(title: String)

    @Update
    suspend fun update(album: Album)

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): LiveData<List<Album>>
}
