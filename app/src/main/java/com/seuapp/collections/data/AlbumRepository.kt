package com.seuapp.collections.data

import kotlinx.coroutines.flow.Flow

class AlbumRepository(private val albumDao: AlbumDao) {

    val allAlbums: Flow<List<Album>> = albumDao.getAllAlbums()

    suspend fun toggleOwned(album: Album) {
        albumDao.updateAlbum(album)
    }

    suspend fun insertAlbum(album: Album) {
        albumDao.insert(album)
    }

    suspend fun deleteAlbum(album: Album) {
        albumDao.delete(album)
    }
}

