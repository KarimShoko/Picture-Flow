package com.example.pix.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PictureDbo>)

    @Query("delete from pictures")
    suspend fun clearAll()

    @Query("select * from pictures")
     fun getAll(): LiveData <List<PictureDbo>>
}