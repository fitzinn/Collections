package com.seuapp.collections.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seuapp.collections.data.Album
import com.seuapp.collections.data.AlbumRepository
import com.seuapp.collections.data.AlbumDatabase
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlbumRepository
    val allAlbums: LiveData<List<Album>>

    init {
        val albumDao = AlbumDatabase.getDatabase(application).albumDao()
        repository = AlbumRepository(albumDao)
        allAlbums = repository.allAlbums.asLiveData() // Convert Flow to LiveData
    }

    fun toggleOwned(album: Album) {
        viewModelScope.launch {
            repository.toggleOwned(album)
        }
    }

    fun addAlbum(newAlbum: Album) {
        viewModelScope.launch {
            repository.insertAlbum(newAlbum)  // Insert album into the database
        }
    }

    // Method to delete an album
    fun deleteAlbum(album: Album) {
        viewModelScope.launch {
            repository.deleteAlbum(album) // Delete album from the database
        }
    }
}

