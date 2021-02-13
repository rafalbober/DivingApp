package com.example.divingapp.Utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.divingapp.Model.UserData
import com.example.divingapp.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class RecyclerAdapterUsers(options: FirebaseRecyclerOptions<UserData>) :
    FirebaseRecyclerAdapter<UserData, RecyclerAdapterUsers.ViewHolder>(options) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemName: TextView = itemView.findViewById(R.id.tv_userName_meeting_list)
        val itemSurname: TextView = itemView.findViewById(R.id.tv_datetime_meeting_list)
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: UserData) {
        holder.itemName.text = model.Name
        holder.itemSurname.text = model.Surname
    }
}
