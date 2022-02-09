package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.domain.UserModel

class RecyclerAdapter(
    private val listMain: List<UserModel>,
    private val listener: (position: String?) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.UserViewHolder>() {

    var userList = mutableListOf<UserModel>()

    fun setUser(users: List<UserModel>) {
        this.userList = users.toMutableList()
    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val tvLogin: TextView = itemView.findViewById(R.id.tvLogin)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val ivAvatar: ImageView = itemView.findViewById((R.id.ivAvatar))

        init {
            ivAvatar.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.invoke(userList[adapterPosition].id)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        Glide.with(holder.itemView.context).load(userList[position].avatar_url)
            .into(holder.ivAvatar)
        holder.tvLogin.text = userList[position].login
        holder.tvId.text = userList[position].id
    }
}