package com.javadsh98.database.room.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Humidity(var value: String, var description: String): Parcelable