package com.seuapp.collections.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.seuapp.collections.data.*
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlbumRepository
    val allAlbums: LiveData<List<Album>>

    init {
        val albumDao = AlbumDatabase.getDatabase(application).albumDao()
        repository = AlbumRepository(albumDao)
        allAlbums = repository.allAlbums
    }

    fun toggleOwned(album: Album) {
        viewModelScope.launch {
            repository.toggleOwned(album.title)
        }
    }

    fun addAlbum(newAlbum: Album) {
        viewModelScope.launch {
            repository.insertAlbum(newAlbum)
        }
    }

    fun deleteAlbum(album: Album) {
        viewModelScope.launch {
            repository.deleteAlbum(album)
        }
    }

    fun updateAlbum(title: String, artist: String?, year: Int?, coverUrl: String?) {
        viewModelScope.launch {
            repository.updateAlbumByTitle(title, artist, year, coverUrl)
        }
    }

    fun deleteAlbum(title: String) {
        viewModelScope.launch {
            repository.deleteAlbumByTitle(title)
        }
    }

}