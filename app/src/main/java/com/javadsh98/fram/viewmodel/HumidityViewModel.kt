package com.javadsh98.fram.viewmodel

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javadsh98.database.room.MyDataBase
import com.javadsh98.database.room.dao.FarmDao
import com.javadsh98.database.room.dao.TreeDao
import com.javadsh98.database.room.entity.Farm
import com.javadsh98.database.room.entity.Tree
import com.javadsh98.database.room.model.Humidity
import io.reactivex.Flowable

class HumidityViewModel(var argument: Bundle): ViewModel(){

    fun getName(): String{
        var farm = argument.getParcelable<Farm>("farm")
        if(farm == null){
            var tree =  argument.getParcelable<Tree>("tree")
            return tree!!.name
        }else{
            return farm.name
        }
    }

    fun getHumidity(): List<Humidity>{
        var farm = argument.getParcelable<Farm>("farm")
        if(farm == null){
            var tree =  argument.getParcelable<Tree>("tree")
            return tree!!.humidity
        }else{
            return farm.humidity
        }
    }

    fun getId(): Int {
        var farm = argument.getParcelable<Farm>("farm")
        if(farm == null){
            var tree =  argument.getParcelable<Tree>("tree")
            return tree!!.id
        }else{
            return farm.id
        }
    }

    fun getModel(): Any {
        var farm = argument.getParcelable<Farm>("farm")
        if(farm == null){
            var tree =  argument.getParcelable<Tree>("tree")
            return tree!!
        }else{
            return farm
        }
    }

    fun getBundle(): Bundle{
        var farm = argument.getParcelable<Farm>("farm")
        if(farm == null){
            var tree =  argument.getParcelable<Tree>("tree")
            return bundleOf("tree" to tree)
        }else{
            return bundleOf("farm" to farm)
        }
    }

}

class HumidityViewModelFactory(var argument: Bundle): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HumidityViewModel(argument) as T
    }
}

