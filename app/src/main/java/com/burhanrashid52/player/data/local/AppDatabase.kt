package com.burhanrashid52.player.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


/**
 * App database
 */
@Database(entities = [(Movies::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}