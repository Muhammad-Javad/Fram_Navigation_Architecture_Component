package com.javadsh98.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.javadsh98.database.room.entity.Tree
import io.reactivex.Flowable

@Dao
interface TreeDao{

    @Insert
    fun insert(tree: Tree)

    @Query("select * from tree_tb")
    fun readTrees(): Flowable<List<Tree>>

}