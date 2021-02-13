package com.example.divingapp.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.divingapp.R

class RecyclerAdapterExample(private var names: List<String>, private var surnames: List<String>, private var images:List<Int>) :
RecyclerView.Adapter<RecyclerAdapterExample.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemName: TextView = itemView.findViewById(R.id.tv_name_users_list)
        val itemSurname: TextView = itemView.findViewById(R.id.tv_surname_users_list)
        val itemPicture: ImageView = itemView.findViewById(R.id.iv_view_users_list)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.single_user_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = names[position]
        holder.itemSurname.text = surnames[position]
        holder.itemPicture.setImageResource(images[position])
    }
}