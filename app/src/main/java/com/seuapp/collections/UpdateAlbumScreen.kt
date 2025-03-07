package com.seuapp.collections

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.platform.LocalContext
import com.seuapp.collections.viewmodel.*
import androidx.navigation.NavController
import com.example.collections.ui.theme.*
import Album

@Composable
fun UpdateAlbumScreen(viewModel: AlbumViewModel, navController: NavController) {
    var title by remember { mutableStateOf("") }
    var artist by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var coverUrl by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var successMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Handle success message after actions like add, update, delete
    LaunchedEffect(successMessage) {
        successMessage.takeIf { it.isNotEmpty() }?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            successMessage = "" // Reset message after showing toast
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Forms for Album Info
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

        // Add Album Button
        Button(
            onClick = {
                if (title.isNotEmpty() && coverUrl.isNotEmpty() && year.isNotEmpty() && artist.isNotEmpty()) {
                    val yearInt = year.toIntOrNull()
                    if (yearInt != null && yearInt >= 1900) {
                        val newAlbum = Album(
                            title = title,
                            coverUrl = coverUrl,
                            year = yearInt,
                            artist = artist,
                            owned = true
                        )
                        viewModel.addAlbum(newAlbum)
                        successMessage = "Album added!" // Show success message

                        // Reset fields after adding album
                        title = ""
                        coverUrl = ""
                        year = ""
                        artist = ""
                    } else {
                        Toast.makeText(context, "Year must be a valid number (>= 1900)!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "All fields must be filled!", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = GreenButton),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
        ) {
            Text("Add Album")
        }

        // Update Album Button
        Button(
            onClick = {
                if (title.isNotEmpty() && artist.isNotEmpty()) {
                    val yearInt = year.toIntOrNull()
                    val updatedYear = if (year.isNotEmpty()) yearInt else null
                    val updatedCoverUrl = if (coverUrl.isNotEmpty()) coverUrl else null

                    isLoading = true
                    viewModel.updateAlbum(title, artist, updatedYear, updatedCoverUrl)
                    successMessage = "Album updated successfully!" // Show success message
                    isLoading = false
                } else {
                    Toast.makeText(context, "Title and Artist are required", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = YellowButton),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Updating..." else "Update Album")
        }

        // Delete Album Button
        Button(
            onClick = {
                if (title.isNotEmpty()) {
                    // Call delete function in the ViewModel
                    viewModel.deleteAlbum(title)
                    successMessage = "Album deleted!" // Show success message

                    // Clear fields
                    title = ""
                    artist = ""
                    year = ""
                    coverUrl = ""
                } else {
                    Toast.makeText(context, "Enter an album title to delete!", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = RedButton),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Delete Album")
        }

        // Navigate Back to Main Screen
        Button(
            onClick = {
                navController.popBackStack() // Pop current screen to go back
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Back to Main Screen")
        }
    }
}

