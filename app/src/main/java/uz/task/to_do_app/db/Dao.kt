package uz.task.to_do_app.db

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Data)

    @Query("select * from data")
    fun getData(): List<Data>?

    @Delete
    fun delete(data: Data)

    @Update
    fun update(data: Data)

}