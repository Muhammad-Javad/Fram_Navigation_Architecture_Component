package com.javadsh98.fram.ui.fragment.humidity

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
import com.javadsh98.fram.ui.fragment.temperature.TemperatureAdapter
import com.javadsh98.fram.viewmodel.HumidityViewModel
import com.javadsh98.fram.viewmodel.HumidityViewModelFactory
import com.javadsh98.fram.viewmodel.TemperatureViewModel
import com.javadsh98.fram.viewmodel.TemperatureViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_humidity.view.*
import kotlinx.android.synthetic.main.fragment_rain_fall.view.*
import kotlinx.android.synthetic.main.fragment_temperature.view.*

class HumidityFragment : Fragment(R.layout.fragment_humidity) {

    private val args : HumidityFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = ViewModelProvider(this, HumidityViewModelFactory(arguments!!))
            .get(HumidityViewModel::class.java)

        var recycler = view.recyclerview_humidity_info
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(context)
        var adapter = HumidityAdapter()
        adapter.onClick = {
        }
//        adapter.submitList(viewmodel.getHumidity())
        adapter.submitList(args.farm?.humidity ?: args.tree?.humidity)
        recycler.adapter = adapter

//        view.floatingactionbutton_humidity_back.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
        view.floatingactionbutton_humidity_forward.setOnClickListener {
//            view.findNavController()
//                .navigate(R.id.action_humidityFragment_to_resultFragment, viewmodel.getBundle())
            val action = HumidityFragmentDirections.actionHumidityFragmentToResultFragment(args.farm
                , args.tree
                , args.tree?.name ?: args.farm?.name!!)
            view.findNavController().navigate(action)

        }

        var activity = requireActivity() as MainActivity
//        activity.supportActionBar!!.title = viewmodel.getName()
//        activity.supportActionBar!!.title = args.farm?.name ?: args.tree?.name
//        activity.setTitle()

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
                            .into(view.imageview_humidity_info)

                        view.textview_Humidity_title.text =
                            resources.getString(R.string.title_humidity_rice_humidity_value)
                    }
                    2 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_wheat_field)
                            .resize(800, 600)
                            .centerInside()
                            .into(view.imageview_humidity_info)

                        view.textview_Humidity_title.text =
                            resources.getString(R.string.title_humidity_wheat_humidity_value)
                    }
                    3 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_straberry)
                            .resize(800, 600)
                            .centerInside()
                            .into(view.imageview_humidity_info)

                        view.textview_Humidity_title.text =
                            resources.getString(R.string.title_humidity_straberry_humidity_value)
                    }
                }
            }
            is Tree -> {

//                when (viewmodel.getId()) {
                when (args.tree?.id) {
                    1 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_orange_tree)
                            .into(view.imageview_humidity_info)

                        view.textview_Humidity_title.text =
                            resources.getString(R.string.title_humidity_orange_humidity_value)
                    }
                }
            }
        }
    }

}
