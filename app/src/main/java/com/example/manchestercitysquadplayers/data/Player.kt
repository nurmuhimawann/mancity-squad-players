package com.example.manchestercitysquadplayers.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String,
    val nationality: String,
    val description: String,
    val photo: Int,
) : Parcelable
