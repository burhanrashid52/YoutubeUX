package com.burhanrashid52.player.data.local

import com.google.gson.annotations.SerializedName

data class MoviesSource(
        @SerializedName("status") var status: Int,
        @SerializedName("source") var source: String = "",
        @SerializedName("movies") var movies: List<Movies> = emptyList())