package com.seuapp.collections.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    var coverUrl: String,
    var artist: String,
    var year: Int,
    var owned: Boolean
)