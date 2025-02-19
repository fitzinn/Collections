package com.seuapp.collections.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.Update

@Dao
interface AlbumDao {

    @Insert
    suspend fun insert(album: Album)

    @Update
    suspend fun updateAlbum(album: Album)

    @Delete
    suspend fun delete(album: Album)

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): Flow<List<Album>>
}
