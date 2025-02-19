package com.seuapp.collections.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.*
import coil.compose.rememberImagePainter
import com.example.collections.ui.theme.*
import com.seuapp.collections.data.Album

@Composable
fun AlbumCatalog(albums: List<Album>, onAlbumClick: (Album) -> Unit) {
    // Group albums by artist
    val albumsByArtist = albums.groupBy { it.artist }

    // Display each group in its own row
    LazyColumn {
        albumsByArtist.forEach { (artist, artistAlbums) ->
            item {
                // Artist Header
                Text(
                    text = artist,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Horizontal Row for Artist's Albums
                LazyRow {
                    items(artistAlbums) { album ->
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .width(150.dp)
                        ) {
                            // Load and display album cover image
                            Image(
                                painter = rememberImagePainter(album.coverUrl),
                                contentDescription = "Album cover",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(bottom = 8.dp),
                                contentScale = ContentScale.Fit
                            )

                            // Album title and year
                            Text(text = album.title, style = MaterialTheme.typography.titleSmall)
                            Text(text = "Year: ${album.year}", style = MaterialTheme.typography.bodySmall)

                            // Owned status button with dynamic color
                            Button(
                                onClick = { onAlbumClick(album) },
                                modifier = Modifier.padding(top = 4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (album.owned) GreenButton else RedButton
                                )
                            ) {
                                Text(text = if (album.owned) "Owned" else "Not Owned")
                            }
                        }
                    }
                }
            }
        }
    }
}