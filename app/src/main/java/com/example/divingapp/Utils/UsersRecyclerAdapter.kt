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

class UsersRecyclerAdapter(options: FirebaseRecyclerOptions<UserData>, onNoteListener: OnNoteListener) :
        FirebaseRecyclerAdapter<UserData, UsersRecyclerAdapter.ViewHolder>(options) {

    var mOnNoteListener = onNoteListener
    inner class ViewHolder(itemView: View, onNoteListener: OnNoteListener) : RecyclerView.ViewHolder(itemView) {

        val itemName: TextView = itemView.findViewById(R.id.tv_name_users_list)
        val itemSurname: TextView = itemView.findViewById(R.id.tv_surname_users_list)
        lateinit var userId: String
//        val itemPicture: ImageView = itemView.findViewById(R.id.iv_view_user_list)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(
                        itemView.context,
                        "You clicked on item # ${position + 1}",
                        Toast.LENGTH_SHORT
                ).show()
                onNoteListener.onUserClick(adapterPosition, userId, itemName.text.toString(), itemSurname.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_user_layout, parent, false)
        return ViewHolder(view, mOnNoteListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: UserData) {
        holder.itemName.text = model.Name
        holder.itemSurname.text = model.Surname
        holder.userId = model.Id.toString()
    }

    interface OnNoteListener
    {
        fun onUserClick(position: Int, userId: String, name: String, surname: String)
    }

}