package com.javadsh98.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.javadsh98.database.room.entity.Farm
import io.reactivex.Flowable

@Dao
interface FarmDao {

    @Insert
    fun insert(farm: Farm)

    @Query("select * from farm_tb")
    fun readFarms(): Flowable<List<Farm>>

}