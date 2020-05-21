package com.javadsh98.fram.ui.fragment.temperature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.javadsh98.database.room.entity.Farm
import com.javadsh98.database.room.entity.Tree
import com.javadsh98.fram.R
import com.javadsh98.fram.ui.MainActivity
import com.javadsh98.fram.ui.fragment.rainfall.RainFallAdapter
import com.javadsh98.fram.viewmodel.TemperatureViewModel
import com.javadsh98.fram.viewmodel.TemperatureViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_humidity.view.*
import kotlinx.android.synthetic.main.fragment_rain_fall.view.*
import kotlinx.android.synthetic.main.fragment_temperature.*
import kotlinx.android.synthetic.main.fragment_temperature.view.*

class TemperatureFragment : Fragment(R.layout.fragment_temperature) {

    private val args: TemperatureFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = ViewModelProvider(this, TemperatureViewModelFactory(arguments!!))
            .get(TemperatureViewModel::class.java)

        var recycler = view.recyclerview_temperature_info
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(context)
        var adapter = TemperatureAdapter()
        adapter.onClick = {
        }
//        adapter.submitList(viewmodel.getTemperature())
        adapter.submitList(args.farm?.temperature ?: args.tree?.temperature)
        recycler.adapter = adapter

//        view.floatingactionbutton_temperature_back.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
        view.floatingactionbutton_temperature_forward.setOnClickListener {
//            view.findNavController().navigate(
//                R.id.action_temperatureFragment_to_humidityFragment,
//                viewmodel.getBundle()
//            )
            val action = TemperatureFragmentDirections.actionTemperatureFragmentToHumidityFragment(args.farm
                , args.tree
                , args.tree?.name ?: args.farm?.name!!)
            view.findNavController().navigate(action)

        }

        var activity = requireActivity() as MainActivity
//        activity.supportActionBar!!.title = viewmodel.getName()
//        activity.setTitle( args.tree?.name ?: args.farm?.name)

        //background
//        when (viewmodel.getModel()) {
        when (args.farm ?: args.tree) {
            is Farm -> {
//                when (viewmodel.getId()) {
                when (args.farm?.id) {
                    1 -> {

                        Picasso.get()
                            .load(R.drawable.bck_all_rice_field)
                            .resize(800, 600)
                            .centerInside()
                            .into(view.imageview_temperature_info)

                        view.textview_temperature_title.text =
                            resources.getString(R.string.title_temperature_rice_temperature_value)
                    }
                    2 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_wheat_field)
                            .resize(800, 600)
                            .centerInside()
                            .into(view.imageview_temperature_info)

                        view.textview_temperature_title.text =
                            resources.getString(R.string.title_temperature_wheat_temperature_value)
                    }
                    3 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_straberry)
                            .resize(800, 600)
                            .centerInside()
                            .into(view.imageview_temperature_info)

                        view.textview_temperature_title.text =
                            resources.getString(R.string.title_temperature_straberry_temperature_value)
                    }
                }
            }
            is Tree -> {
//                when (viewmodel.getId()) {
                when (args.tree?.id) {
                    1 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_orange_tree)
                            .into(view.imageview_temperature_info)

                        view.textview_temperature_title.text =
                            resources.getString(R.string.title_temperature_orange_temperature_value)
                    }
                }
            }
        }
    }

}
