package com.javadsh98.database.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javadsh98.database.room.model.Humidity
import com.javadsh98.database.room.model.RainFall
import com.javadsh98.database.room.model.Temperature
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tree_tb")
data class Tree(@PrimaryKey(autoGenerate = true) var id: Int
                , var name: String
                , var rainfall: List<RainFall>
                , var temperature: List<Temperature>
                , var humidity: List<Humidity>
                , var description: String): Parcelable