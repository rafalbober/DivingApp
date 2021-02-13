package com.example.divingapp.Utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.divingapp.Model.Meeting
import com.example.divingapp.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class MeetingsRecyclerAdapter(options: FirebaseRecyclerOptions<Meeting>) :
    FirebaseRecyclerAdapter<Meeting, MeetingsRecyclerAdapter.ViewHolder>(options) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemUser: TextView = itemView.findViewById(R.id.tv_user_meeting_list)
        val itemDateTime: TextView = itemView.findViewById(R.id.tv_datetime_meeting_list)
        val itemInfo: TextView = itemView.findViewById(R.id.tv_description_meeting_list)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_meeting_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Meeting) {
        holder.itemUser.text = model.UserName + " " + model.UserSurname
        holder.itemDateTime.text = model.Date + ", " + model.Time
        holder.itemInfo.text = model.Info
    }
}
