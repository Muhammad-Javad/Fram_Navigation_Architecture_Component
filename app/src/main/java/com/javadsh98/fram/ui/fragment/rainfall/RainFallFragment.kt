package com.javadsh98.fram.ui.fragment.rainfall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.javadsh98.database.room.entity.Farm
import com.javadsh98.database.room.entity.Tree
import com.javadsh98.fram.R
import com.javadsh98.fram.ui.MainActivity
import com.javadsh98.fram.ui.fragment.MainFragmentDirections
import com.javadsh98.fram.viewmodel.RainFallViewModel
import com.javadsh98.fram.viewmodel.RainFallViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_humidity.view.*
import kotlinx.android.synthetic.main.fragment_rain_fall.view.*

class RainFallFragment : Fragment(R.layout.fragment_rain_fall) {

    private val args: RainFallFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = ViewModelProvider(this, RainFallViewModelFactory(arguments!!))
            .get(RainFallViewModel::class.java)

        var recycler = view.recyclerview_rainfall_info
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(context)
        var adapter = RainFallAdapter()
        adapter.onClick = {
        }
//        adapter.submitList(viewmodel.getRainFall())
        adapter.submitList(args.farm?.rainfall ?: args.tree?.rainfall)
        recycler.adapter = adapter


        view.floatingactionbutton_rainfall_next.setOnClickListener {
            val action = RainFallFragmentDirections.actionRainFallFragmentToTemperatureFragment(args.farm
                , args.tree
                , args.tree?.name ?: args.farm?.name!!)
//            view.findNavController().navigate(
//                R.id.action_rainFallFragment_to_temperatureFragment
//                , viewmodel.getBundle()
//            )

            view.findNavController().navigate(action
//                , NavOptions.Builder().setPopUpTo(R.id.rainFallFragment, true).build()
            )
//            navigator!!.toTemperature(args.farm, args.tree)
        }

//        view.floatingactionbutton_rainfall_back.setOnClickListener {
//            requireActivity().onBackPressed()
//            navigator!!.back()
//        }

        var activity = requireActivity() as MainActivity
//        activity.supportActionBar!!.title = viewmodel.getName()
//        activity.supportActionBar!!.title = args.farm?.name ?: args.tree?.name
//        activity.setTitle(args.tree?.name ?: args.farm?.name)

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
                            .into(view.imageview_rainfall_info)

                        view.textview_rainfall_title.text =
                            resources.getString(R.string.title_rainfall_rice_rainfall_value)
                    }
                    2 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_wheat_field)
                            .resize(800, 600)
                            .centerInside()
                            .into(view.imageview_rainfall_info)

                        view.textview_rainfall_title.text =
                            resources.getString(R.string.title_rainfall_wheat_rainfall_value)
                    }
                    3 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_straberry)
                            .resize(800, 600)
                            .centerInside()
                            .into(view.imageview_rainfall_info)

                        view.textview_rainfall_title.text =
                            resources.getString(R.string.title_rainfall_straberry_rainfall_value)
                    }
                }
            }
            is Tree -> {
//                when (viewmodel.getId()) {
                when (args.tree?.id) {
                    1 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_orange_tree)
                            .into(view.imageview_rainfall_info)

                        view.textview_rainfall_title.text =
                            resources.getString(R.string.title_rainfall_orange_rainfall_value)
                    }
                }
            }
        }
    }

}
