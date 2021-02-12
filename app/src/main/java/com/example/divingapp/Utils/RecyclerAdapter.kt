package com.example.divingapp.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.divingapp.Model.Users
import com.example.divingapp.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class RecyclerAdapter(options: FirebaseRecyclerOptions<Users>) :
    FirebaseRecyclerAdapter<Users, RecyclerAdapter.ViewHolder>(options) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemName: TextView = itemView.findViewById(R.id.tv_name_user_list)
        val itemSurname: TextView = itemView.findViewById(R.id.tv_surname_user_list)
//        val itemPicture: ImageView = itemView.findViewById(R.id.iv_view_user_list)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "You clicked on item # ${position + 1}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_user_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Users) {
        holder.itemName.text = model.Name
        holder.itemSurname.text = model.Surname
    }
}
