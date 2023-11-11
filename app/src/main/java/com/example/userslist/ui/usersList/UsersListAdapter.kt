package com.example.userslist.ui.usersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userslist.R
import com.example.userslist.databinding.ItemUserBinding
import com.example.userslist.ui.model.User

class UsersListAdapter(private val users: MutableList<User>, private val onUserClick : (User) -> Unit) :
    ListAdapter<User, UsersListAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            with(binding) {
                if(!item.secondName.isNullOrEmpty())
                    userName.text = root.context.getString(R.string.user_name, item.name, item.secondName)
                else
                    userName.text = item.name
                phoneNumber.text = item.phoneNumber
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = users[position]
        holder.itemView.setOnClickListener {
            onUserClick(item)
        }
        holder.bind(item)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}