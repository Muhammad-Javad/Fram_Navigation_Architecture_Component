package com.javadsh98.fram.ui.fragment.humidity

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.database.room.model.Humidity
import com.javadsh98.fram.R
import kotlinx.android.synthetic.main.item_all_info.view.*

class HumidityAdapter: ListAdapter<Humidity, HumidityViewHolder>(HumidityAdapter.diff){

    lateinit var onClick: (Any) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HumidityViewHolder {
        return HumidityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_all_info, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HumidityViewHolder, position: Int) {
        var Humidity = getItem(position)
        holder.onBind(Humidity, onClick)
    }

    companion object{
        val diff = object: DiffUtil.ItemCallback<Humidity>(){
            override fun areItemsTheSame(oldItem: Humidity, newItem: Humidity): Boolean {
                return TextUtils.equals(oldItem.value, newItem.value)
            }

            override fun areContentsTheSame(oldItem: Humidity, newItem: Humidity): Boolean {
                return TextUtils.equals(oldItem.value, newItem.value)
                        && TextUtils.equals(oldItem.description, newItem.description)
            }

        }

    }

}

class HumidityViewHolder(view: View) : RecyclerView.ViewHolder(view){

    fun onBind(Humidity: Humidity, onclick: (any: Any) -> Unit){
        itemView.textview_all_item_title.text = Humidity.value
        itemView.textview_all_item_response.text = Humidity.description
        itemView.setOnClickListener{
            onclick.invoke(Humidity)
            itemView.textview_all_item_response.visibility = View.VISIBLE
        }
    }

}