package com.javadsh98.fram.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.javadsh98.database.room.entity.Farm
import com.javadsh98.database.room.entity.Tree
import com.javadsh98.fram.R
import com.javadsh98.fram.databinding.ActivityMainBinding
import com.javadsh98.fram.ui.fragment.MainFragmentDirections
import com.javadsh98.fram.ui.fragment.product.ProductFragment
import com.javadsh98.fram.ui.fragment.product.ProductFragmentDirections

class MainActivity : AppCompatActivity() {

//    private val binding:
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val graph = findNavController(R.id.fragment_main).graph
        val appBarConfiguration = AppBarConfiguration(graph)
        //connect
        val controller = findNavController(R.id.fragment_main)
        binding.toolbarMain.setupWithNavController(controller, appBarConfiguration)
    }

}
