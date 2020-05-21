package com.javadsh98.fram.ui.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.javadsh98.database.room.entity.Farm
import com.javadsh98.database.room.entity.Tree
import com.javadsh98.fram.R
import com.javadsh98.fram.ui.MainActivity
import com.javadsh98.fram.viewmodel.ResultViewModel
import com.javadsh98.fram.viewmodel.ResultViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_humidity.view.*
import kotlinx.android.synthetic.main.fragment_rain_fall.view.*
import kotlinx.android.synthetic.main.fragment_result.view.*

class ResultFragment : Fragment(R.layout.fragment_result) {

    private val args: ResultFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = ViewModelProvider(this, ResultViewModelFactory(arguments!!))
            .get(ResultViewModel::class.java)

        var resultView = view.textview_result_text
        resultView.movementMethod = ScrollingMovementMethod()
//        resultView.text = viewmodel.getResult()
        resultView.text = args.tree?.description ?: args.farm?.description

//        view.floatingactionbutton_result_back.setOnClickListener {
//            requireActivity().onBackPressed()
//        }

        var activity = requireActivity() as MainActivity
//        activity.supportActionBar!!.title = viewmodel.getName()
//        activity.supportActionBar!!.title = args.tree?.name ?: args.farm?.name
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
                            .into(view.imageview_result_info)
                    }
                    2 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_wheat_field)
                            .resize(800, 600)
                            .centerInside()
                            .into(view.imageview_result_info)
                    }
                    3 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_straberry)
                            .resize(800, 600)
                            .centerInside()
                            .into(view.imageview_result_info)
                    }
                }
            }
            is Tree -> {
//                when (viewmodel.getId()) {
                when (args.tree?.id) {
                    1 -> {
                        Picasso.get()
                            .load(R.drawable.bck_all_orange_tree)
                            .into(view.imageview_result_info)
                    }
                }
            }
        }

    }

}
