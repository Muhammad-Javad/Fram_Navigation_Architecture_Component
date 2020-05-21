package com.javadsh98.fram.ui.fragment.temperature

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.database.room.model.Temperature
import com.javadsh98.fram.R
import kotlinx.android.synthetic.main.item_all_info.view.*

class TemperatureAdapter: ListAdapter<Temperature, TemperatureViewHolder>(TemperatureAdapter.diff){

    lateinit var onClick: (Any) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperatureViewHolder {
        return TemperatureViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_all_info, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TemperatureViewHolder, position: Int) {
        var Temperature = getItem(position)
        holder.onBind(Temperature, onClick)
    }

    companion object{
        val diff = object: DiffUtil.ItemCallback<Temperature>(){
            override fun areItemsTheSame(oldItem: Temperature, newItem: Temperature): Boolean {
                return TextUtils.equals(oldItem.value, newItem.value)
            }

            override fun areContentsTheSame(oldItem: Temperature, newItem: Temperature): Boolean {
                return TextUtils.equals(oldItem.value, newItem.value)
                        && TextUtils.equals(oldItem.description, newItem.description)
            }

        }

    }

}

class TemperatureViewHolder(view: View) : RecyclerView.ViewHolder(view){

    fun onBind(Temperature: Temperature, onclick: (any: Any) -> Unit){
        itemView.textview_all_item_title.text = Temperature.value
        itemView.textview_all_item_response.text = Temperature.description
        itemView.setOnClickListener{
            onclick.invoke(Temperature)
            itemView.textview_all_item_response.visibility = View.VISIBLE
        }
    }

}