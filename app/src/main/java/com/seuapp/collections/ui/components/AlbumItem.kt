package com.seuapp.collections.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import coil.compose.rememberImagePainter
import com.seuapp.collections.data.Album
import androidx.compose.material3.*
import androidx.compose.foundation.shape.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*

@Composable
fun AlbumItem(album: Album, onAlbumClick: (Album) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .clickable { onAlbumClick(album) }
    ) {
        // Album Cover Image on Top
        Image(
            painter = rememberImagePainter(album.coverUrl),
            contentDescription = album.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        )

        // Title and Year Text below the image
        Column(
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                text = album.title,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Ano: ${album.year}",
                color = Color.Black,
                fontSize = 12.sp
            )
        }
    }
}
