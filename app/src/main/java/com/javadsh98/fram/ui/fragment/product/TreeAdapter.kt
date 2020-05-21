package com.javadsh98.fram.ui.fragment.product

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.database.room.entity.Tree
import com.javadsh98.fram.R
import kotlinx.android.synthetic.main.item_all_product.view.*

class TreeAdapter : ListAdapter<Tree, TreeViewHolder>(TreeAdapter.diff){

    lateinit var onClick : (Any) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {
        return TreeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_all_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {
        holder.onBind(getItem(position), onClick)
    }

    companion object{

        val diff = object: DiffUtil.ItemCallback<Tree>(){

            override fun areItemsTheSame(oldItem: Tree, newItem: Tree): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Tree, newItem: Tree): Boolean {
                return oldItem.id == newItem.id
                        && TextUtils.equals(oldItem.name, newItem.name)
            }
        }
    }
}

class TreeViewHolder(view: View): RecyclerView.ViewHolder(view){

    fun onBind(Tree: Tree, onClick: (Any) -> Unit){
        itemView.button_product_type.text = Tree.name
        itemView.button_product_type.setOnClickListener {
            onClick.invoke(Tree)
        }
    }
}