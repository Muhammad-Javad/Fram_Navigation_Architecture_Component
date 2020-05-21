package com.javadsh98.fram.viewmodel

import android.app.Application
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
import io.reactivex.Flowable

class ProductViewModel(application: Application, var argument: Bundle) : AndroidViewModel(application){

    private val db = MyDataBase.getInstance(application)
    private var farmDao: FarmDao = db.getFarmDao()
    private var treeDao: TreeDao = db.getTreeDao()

    fun getType() = argument.getInt("type")

    fun getFarmList(): Flowable<List<Farm>>{
        return farmDao.readFarms()
    }

    fun getTreeList(): Flowable<List<Tree>>{
        return treeDao.readTrees()
    }

    fun getFarmBundle(farm: Farm): Bundle {
        return bundleOf("farm" to farm)
    }

    fun getTreeBundle(tree: Tree): Bundle {
        return bundleOf("tree" to tree)
    }
}

class ProductViewModelFactory(var application: Application, var argument: Bundle): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(application, argument) as T
    }
}