package com.seuapp.collections

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.seuapp.collections.ui.screens.AlbumCatalog
import com.seuapp.collections.viewmodel.AlbumViewModel
import com.seuapp.collections.data.Album

class MainActivity : ComponentActivity() {
    private val albumViewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    MainScreen(albumViewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(albumViewModel: AlbumViewModel) {
    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var coverUrl by remember { mutableStateOf("") }
    var artist by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Album Title") },
            modifier = Modifier.padding(top = 8.dp)
        )

        OutlinedTextField(
            value = coverUrl,
            onValueChange = { coverUrl = it },
            label = { Text("Cover URL") },
            modifier = Modifier.padding(top = 8.dp)
        )

        OutlinedTextField(
            value = artist,
            onValueChange = { artist = it },
            label = { Text("Artist") },
            modifier = Modifier.padding(top = 8.dp)
        )

        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Release Year") },
            modifier = Modifier.padding(top = 8.dp)
        )

        Button(
            onClick = {
                if (title.isNotEmpty() && coverUrl.isNotEmpty() && year.isNotEmpty() && artist.isNotEmpty()) {
                    val yearInt = year.toIntOrNull()
                    if (yearInt != null) {
                        val newAlbum = Album(
                            title = title,
                            coverUrl = coverUrl,
                            year = yearInt,
                            artist = artist,
                            owned = true
                        )
                        albumViewModel.addAlbum(newAlbum)
                        Toast.makeText(context, "Album added!", Toast.LENGTH_SHORT).show()

                        title = ""
                        coverUrl = ""
                        year = ""
                        artist = "" // Clear artist field
                    } else {
                        Toast.makeText(context, "Year must be a number!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "All fields must be filled!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add New Album")
        }

        // New Button to Delete the Last Album Added
        Button(
            onClick = {
                // Get the last album added
                val lastAlbum = albumViewModel.allAlbums.value?.lastOrNull()

                // If there's an album to delete
                lastAlbum?.let {
                    albumViewModel.deleteAlbum(it)
                    Toast.makeText(context, "Last album deleted!", Toast.LENGTH_SHORT).show()
                } ?: run {
                    Toast.makeText(context, "No album to delete!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Delete Last Album")
        }

        AlbumCatalog(
            albums = albumViewModel.allAlbums.observeAsState(emptyList()).value,
            onAlbumClick = { albumViewModel.toggleOwned(it) }
        )
    }
}