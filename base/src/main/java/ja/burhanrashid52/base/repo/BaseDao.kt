package ja.burhanrashid52.base.repo

import android.arch.persistence.room.*

@Dao
interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg data: T)

    @Delete
    fun delete(vararg data: T)

    @Update
    fun update(vararg data: T)
}
