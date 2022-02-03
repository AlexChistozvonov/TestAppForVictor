package com.example.testapp
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val context: Context, private val listMain: MutableList<UserModel>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvLogin: TextView = itemView.findViewById(R.id.tvLogin)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val ivAvatar: ImageView = itemView.findViewById((R.id.ivAvatar))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = listMain.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvLogin.text = listMain[position].login
        holder.tvId.text = listMain[position].id
    }
}