package com.javadsh98.fram.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.javadsh98.fram.R
import com.javadsh98.fram.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment(R.layout.fragment_main) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.button_main_farm.setOnClickListener {
//            it.findNavController()
//                .navigate(R.id.action_mainFragment_to_productFragment, bundleOf("type" to 1))
            val action = MainFragmentDirections.actionMainFragmentToProductFragment(1)
            view.findNavController().navigate(action)
        }
        view.button_main_tree.setOnClickListener {
//            it.findNavController()
//                .navigate(R.id.action_mainFragment_to_productFragment, bundleOf("type" to 2))
            val action = MainFragmentDirections.actionMainFragmentToProductFragment(2)
            view.findNavController().navigate(action)
        }

        var activity = requireActivity() as MainActivity
//        activity.supportActionBar!!.title = requireActivity().resources.getString(R.string.app_name)
//        activity.setTitle(requireActivity().resources.getString(R.string.app_name))
    }
}
