package com.seuapp.collections.data

class AlbumRepository(private val albumDao: AlbumDao) {

    val allAlbums = albumDao.getAllAlbums()

    // Insert a new album
    suspend fun insertAlbum(album: Album) {
        albumDao.insert(album)
    }

    // Delete an album
    suspend fun deleteAlbum(album: Album) {
        albumDao.delete(album)
    }

    // Toggle the 'owned' status of an album
    suspend fun toggleOwned(title: String) {
        val album = albumDao.getAlbumByTitle(title)
        album?.let {
            it.owned = !it.owned
            albumDao.update(it) // Update the album with the new ownership status
        }
    }

    // Update album by title
    suspend fun updateAlbumByTitle(
        title: String,
        newArtist: String?,
        newYear: Int?,
        newCoverUrl: String?
    ) {
        val album = albumDao.getAlbumByTitle(title)
        album?.let {
            if (newArtist != null) it.artist = newArtist
            if (newYear != null) it.year = newYear
            if (newCoverUrl != null) it.coverUrl = newCoverUrl
            albumDao.update(it)
        }
    }

    suspend fun deleteAlbumByTitle(title: String) {
        val album = albumDao.getAlbumByTitle(title) // Fetch the album by title
        if (album != null) {
            albumDao.delete(album) // Delete if found
        }
    }
}

