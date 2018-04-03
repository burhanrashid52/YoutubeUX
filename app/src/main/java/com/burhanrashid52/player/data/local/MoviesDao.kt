package com.burhanrashid52.player.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import ja.burhanrashid52.base.repo.BaseDao

@Dao
interface MoviesDao : BaseDao<Movies> {

    /**
     * Insert movies into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movies>): List<Long>

    /**
     * Get all the movies from database
     */
    @Query("SELECT * FROM Movies")
    fun getMovies(): LiveData<List<Movies>>


    /**
     * Get the
     */
    @Query("SELECT * FROM Movies WHERE id=:movieId")
    fun getMoviesDetails(movieId: Int): LiveData<Movies>
}