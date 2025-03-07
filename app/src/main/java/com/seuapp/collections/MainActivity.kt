package com.seuapp.collections

import Album
import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.*
import com.seuapp.collections.ui.screens.*
import com.seuapp.collections.viewmodel.AlbumViewModel

class MainActivity : ComponentActivity() {
    private val albumViewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    // Pass NavHostController to handle navigation
                    MainScreen(albumViewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(albumViewModel: AlbumViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            MainContent(albumViewModel, navController)
        }
        composable("update_album_screen") {
            UpdateAlbumScreen(albumViewModel, navController)
        }
    }
}

@Composable
fun MainContent(albumViewModel: AlbumViewModel, navController: NavController) {
    val context = LocalContext.current // Retrieve context here

    // Use a State to observe albums from the repository
    var albums by remember { mutableStateOf(listOf<Album>()) }

    // LaunchedEffect to fetch albums on first composition
    LaunchedEffect(Unit) {
        albums = albumViewModel.getAllAlbums() // Get all albums from MongoDB
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                navController.navigate("update_album_screen")
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
        ) {
            Text("Album Manager")
        }

        AlbumCatalog(
            albums = albums,
            onAlbumClick = { albumViewModel.toggleOwned(it) }
        )
    }
}