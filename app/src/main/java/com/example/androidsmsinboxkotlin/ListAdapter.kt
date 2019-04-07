package com.example.androidsmsinboxkotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidsmsinboxkotlin.ListAdapter.MyViewHolder
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val myDatabase: Array<String>): RecyclerView.Adapter<MyViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myDatabase.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.textView.text = "title"
        holder.itemView.textView.text = "message"
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private var view: View? = null
        init {
            view = itemView
        }

    }


}