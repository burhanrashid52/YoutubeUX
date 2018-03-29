package com.burhanrashid52.player.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Movies(

        @field:SerializedName("id")
        @PrimaryKey
        val id: Int,

        @field:SerializedName("videoUrl")
        val videoUrl: String = "",

        @field:SerializedName("description")
        val description: String = "",

        @field:SerializedName("title")
        val title: String = "",

        @field:SerializedName("poster")
        val poster: String = ""
)