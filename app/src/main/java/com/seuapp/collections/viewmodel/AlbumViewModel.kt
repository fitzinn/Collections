package com.seuapp.collections.viewmodel

import Album
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.seuapp.collections.data.AlbumRepository
import com.seuapp.collections.data.MongoDBConnection
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlbumRepository

    init {
        val mongoDBConnection = MongoDBConnection()
        repository = AlbumRepository(mongoDBConnection)
    }

    // Função para alternar o status de 'owned' de um álbum
    fun toggleOwned(album: Album) {
        viewModelScope.launch {
            repository.toggleOwned(album.title)
        }
    }

    // Função para adicionar um novo álbum
    fun addAlbum(newAlbum: Album) {
        viewModelScope.launch {
            repository.insertAlbum(newAlbum)
        }
    }

    // Função para atualizar um álbum
    fun updateAlbum(title: String, artist: String?, year: Int?, coverUrl: String?) {
        viewModelScope.launch {
            repository.updateAlbumByTitle(title, artist, year, coverUrl)
        }
    }

    // Função para excluir um álbum pelo título
    fun deleteAlbum(title: String) {
        viewModelScope.launch {
            repository.deleteAlbum(title)
        }
    }

    // Função para obter todos os álbuns
    fun getAllAlbums(): List<Album> {
        return repository.getAllAlbums()
    }
}