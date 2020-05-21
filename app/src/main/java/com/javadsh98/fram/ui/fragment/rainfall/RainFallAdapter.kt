package com.javadsh98.fram.ui.fragment.rainfall

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.database.room.model.RainFall
import com.javadsh98.fram.R
import kotlinx.android.synthetic.main.item_all_info.view.*

class RainFallAdapter: ListAdapter<RainFall, RainFallViewHolder>(RainFallAdapter.diff){

    lateinit var onClick: (Any) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RainFallViewHolder {
        return RainFallViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_all_info, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RainFallViewHolder, position: Int) {
        var rainFall = getItem(position)
        holder.onBind(rainFall, onClick)
    }

    companion object{
        val diff = object: DiffUtil.ItemCallback<RainFall>(){
            override fun areItemsTheSame(oldItem: RainFall, newItem: RainFall): Boolean {
                return TextUtils.equals(oldItem.level, newItem.level)
            }

            override fun areContentsTheSame(oldItem: RainFall, newItem: RainFall): Boolean {
                return TextUtils.equals(oldItem.level, newItem.level)
                        && TextUtils.equals(oldItem.description, newItem.description)
            }

        }

    }

}

class RainFallViewHolder(view: View) : RecyclerView.ViewHolder(view){

    fun onBind(rainFall: RainFall, onclick: (any: Any) -> Unit){
        itemView.textview_all_item_title.text = rainFall.level
        itemView.textview_all_item_response.text = rainFall.description
        itemView.setOnClickListener{
            onclick.invoke(rainFall)
            itemView.textview_all_item_response.visibility = View.VISIBLE
        }
    }

}