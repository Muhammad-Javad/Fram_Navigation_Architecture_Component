package com.javadsh98.fram.ui.fragment.product

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.database.room.entity.Farm
import com.javadsh98.fram.R
import kotlinx.android.synthetic.main.item_all_product.view.*

class FarmAdapter : ListAdapter<Farm, FarmViewHolder>(FarmAdapter.diff){

    lateinit var onClick : (Any) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmViewHolder {
        return FarmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_all_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FarmViewHolder, position: Int) {
        holder.onBind(getItem(position), onClick)
    }

    companion object{

        val diff = object: DiffUtil.ItemCallback<Farm>(){

            override fun areItemsTheSame(oldItem: Farm, newItem: Farm): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Farm, newItem: Farm): Boolean {
                return oldItem.id == newItem.id
                        && TextUtils.equals(oldItem.name, newItem.name)
            }
        }
    }
}

class FarmViewHolder(view: View): RecyclerView.ViewHolder(view){

    fun onBind(farm: Farm, onClick: (Any) -> Unit){
        itemView.button_product_type.text = farm.name
        itemView.button_product_type.setOnClickListener {
            onClick.invoke(farm)
        }
    }
}