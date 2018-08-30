package com.burhanrashid52.player.dbTest

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.burhanrashid52.player.data.local.AppDatabase
import com.burhanrashid52.player.data.local.Movies
import com.burhanrashid52.player.data.local.MoviesDao
import com.burhanrashid52.player.util.LiveDataTestUtil
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException


class MovieDbTest {
    private lateinit var mMoviesDao: MoviesDao
    private lateinit var mDb: AppDatabase
    private lateinit var allMovies: List<Movies>


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        mMoviesDao = mDb.moviesDao()
        allMovies = FakeData.createMovies()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertMovies() {
        mMoviesDao.insertMovies(allMovies)
        val movies = mMoviesDao.getMovies()
        val moviesDb = LiveDataTestUtil.getValue(movies)
        moviesDb.forEach {
            assertTrue(allMovies.contains(it))
        }
    }

    @Test
    @Throws(Exception::class)
    fun fetchMovieById() {
        mMoviesDao.insertMovies(allMovies)
        val movieItem = allMovies[0]
        val movies = mMoviesDao.getMoviesDetails(movieItem.id)
        val moviesDb = LiveDataTestUtil.getValue(movies)
        assertEquals(movieItem, moviesDb)
    }
}