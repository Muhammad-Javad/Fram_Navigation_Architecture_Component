package com.javadsh98.fram.ui.fragment.product

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
import com.javadsh98.fram.viewmodel.ProductViewModel
import com.javadsh98.fram.viewmodel.ProductViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_product.view.*

class ProductFragment : Fragment(R.layout.fragment_product) {

    val args: ProductFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = ViewModelProvider(this, ProductViewModelFactory(requireActivity().application, arguments!!))
            .get(ProductViewModel::class.java)


        var recyclerView = view.recycler_product_type
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        when(args.type){ // instead of viewmodel.getType()
            1 -> {
                //farm
                viewmodel.getFarmList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {

                        var adapter = FarmAdapter()
                        adapter.onClick = {
                            it as Farm
                            val action = ProductFragmentDirections.actionProductFragmentToRainFallFragment(it
                                , null
                                , it.name)
//                            view.findNavController().navigate(R.id.action_productFragment_to_rainFallFragment, viewmodel.getFarmBundle(it))
                            view.findNavController().navigate(action)
                        }
                        adapter.submitList(it)
                        recyclerView.adapter = adapter
                    }
            }
            2 -> {
                //tree
                viewmodel.getTreeList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {

                        var adapter = TreeAdapter()
                        adapter.onClick = {
                            it as Tree
                            val action = ProductFragmentDirections.actionProductFragmentToRainFallFragment(null
                                , it
                                , it.name)
//                            view.findNavController().navigate(R.id.action_productFragment_to_rainFallFragment, viewmodel.getTreeBundle(it))
                            view.findNavController().navigate(action)
                        }
                        adapter.submitList(it)
                        recyclerView.adapter = adapter
                    }
            }
        }

//        view.floatingactionbutton_product_back.setOnClickListener {
//            requireActivity().onBackPressed()
//        }

        var activity = requireActivity() as MainActivity
//        activity.supportActionBar!!.title = requireActivity().resources.getString(R.string.app_name)
//        activity.setTitle(requireActivity().resources.getString(R.string.app_name))

    }

}
