package com.example.foodhall.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foodhall.R
import com.example.foodhall.datamodel.CategoryModel
import com.example.foodhall.datamodel.Data
import kotlinx.android.synthetic.main.row_item_bottom.view.*

class BottomAdapter (val menuItemList : ArrayList<Data>): RecyclerView.Adapter<BottomAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup?.context).inflate(R.layout.row_item_bottom, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(menuItemList[position].image)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(holder.iv_item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val iv_item = itemView.iv_BottomDetails
    }
}