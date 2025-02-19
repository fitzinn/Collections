package com.seuapp.collections.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.*
import com.seuapp.collections.data.*
import com.seuapp.collections.viewmodel.*

@Composable
fun UpdateAlbumScreen(viewModel: AlbumViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var artist by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var coverUrl by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Album Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = artist,
            onValueChange = { artist = it },
            label = { Text("Artist") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Year") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = coverUrl,
            onValueChange = { coverUrl = it },
            label = { Text("Cover URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (title.isNotEmpty() && artist.isNotEmpty()) {
                    val yearInt = year.toIntOrNull()
                    // Only update fields that are filled
                    val updatedYear = if (year.isNotEmpty()) yearInt else null
                    val updatedCoverUrl = if (coverUrl.isNotEmpty()) coverUrl else null

                    isLoading = true
                    // Update album with only the non-empty fields
                    viewModel.updateAlbum(title, artist, updatedYear, updatedCoverUrl)
                    Toast.makeText(context, "Album updated successfully!", Toast.LENGTH_SHORT).show()
                    isLoading = false
                } else {
                    Toast.makeText(context, "Title and Artist are required", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Updating..." else "Update Album")
        }

        // Move Add Album functionality to Update Album screen if needed
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
                        viewModel.addAlbum(newAlbum)
                        Toast.makeText(context, "Album added!", Toast.LENGTH_SHORT).show()

                        // Reset fields after adding album
                        title = ""
                        coverUrl = ""
                        year = ""
                        artist = ""
                    } else {
                        Toast.makeText(context, "Year must be a number!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "All fields must be filled!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
        ) {
            Text("Add New Album")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpdateAlbumScreenPreview() {
    UpdateAlbumScreen()
}