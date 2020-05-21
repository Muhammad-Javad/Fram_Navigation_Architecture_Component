package com.javadsh98.fram.viewmodel

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javadsh98.database.room.entity.Farm
import com.javadsh98.database.room.entity.Tree

class ResultViewModel(var argument: Bundle): ViewModel(){

    fun getName(): String{
        var farm = argument.getParcelable<Farm>("farm")
        if(farm == null){
            var tree =  argument.getParcelable<Tree>("tree")
            return tree!!.name
        }else{
            return farm.name
        }
    }

    fun getResult(): String{
        var farm = argument.getParcelable<Farm>("farm")
        if(farm == null){
            var tree =  argument.getParcelable<Tree>("tree")
            return tree!!.description
        }else{
            return farm.description
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

class ResultViewModelFactory(var argument: Bundle): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultViewModel(argument) as T
    }
}

