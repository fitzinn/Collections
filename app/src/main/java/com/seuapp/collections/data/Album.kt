package com.seuapp.collections.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val coverUrl: String,
    val artist: String,
    val year: Int,
    val owned: Boolean
)