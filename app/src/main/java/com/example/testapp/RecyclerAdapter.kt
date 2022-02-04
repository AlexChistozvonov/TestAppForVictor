package com.example.testapp
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter(private val listMain: MutableList<UserModel>, private val listener: (position : String?) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.UserViewHolder>() {



   inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvLogin: TextView = itemView.findViewById(R.id.tvLogin)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val ivAvatar: ImageView = itemView.findViewById((R.id.ivAvatar))

        init{
            ivAvatar.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.invoke(listMain[adapterPosition].id)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount() = listMain.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        Glide.with(holder.itemView.context).load(listMain[position].avatar_url).into(holder.ivAvatar)
        holder.tvLogin.text = listMain[position].login
        holder.tvId.text = listMain[position].id
    }
}