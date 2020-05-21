package com.javadsh98.database.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.javadsh98.database.room.model.Humidity
import com.javadsh98.database.room.model.RainFall
import com.javadsh98.database.room.model.Temperature
import java.lang.reflect.Type
import java.util.*


class Converter{

    companion object{

        //rainfall
        @JvmStatic
        @TypeConverter
        fun stringToRainFalls(rainFallStr: String): List<RainFall>{
            if (rainFallStr == null) {
                return Collections.emptyList()
            }
            var gson = Gson()
            val listType : Type=
                object : TypeToken<List<RainFall>>() {}.type
            return gson.fromJson(rainFallStr, listType)
        }

        @JvmStatic
        @TypeConverter
        fun RainFallsToString(rainFalls: List<RainFall>): String{
            return Gson().toJson(rainFalls)
        }

        //temperature
        @TypeConverter
        @JvmStatic
        fun stringToTemperatures(temperatureStr: String): List<Temperature>{
            if (temperatureStr == null) {
                return Collections.emptyList()
            }
            var gson = Gson()
            val listType : Type=
                object : TypeToken<List<Temperature>>() {}.type

            return gson.fromJson(temperatureStr, listType)
        }

        @TypeConverter
        @JvmStatic
        fun temperaturesToString(temperatures: List<Temperature>): String{
            return Gson().toJson(temperatures)
        }

        //Humidity
        @TypeConverter
        @JvmStatic
        fun stringToHumidity(humidityStr: String): List<Humidity>{
            if (humidityStr == null) {
                return Collections.emptyList()
            }
            var gson = Gson()
            val listType : Type=
                object : TypeToken<List<Humidity>>() {}.type

            return gson.fromJson(humidityStr, listType)
        }

        @TypeConverter
        @JvmStatic
        fun humidityToString(humidity: List<Humidity>): String{
            return Gson().toJson(humidity)
        }
    }

}