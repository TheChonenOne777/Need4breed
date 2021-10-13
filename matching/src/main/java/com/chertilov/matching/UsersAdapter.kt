package com.chertilov.matching

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chertilov.core_api.dto.User
import com.chertilov.matching.databinding.ItemUserBinding

class UsersAdapter(private val clickListener: UserlickListener) : ListAdapter<User, UsersAdapter.UserViewHolder>(UsersDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context)))
            .apply { itemView.setOnClickListener { clickListener.onUserClicked(getItem(adapterPosition)) } }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            Glide.with(binding.root.context)
                .load(user.image)
                .into(binding.image)
            binding.name.text = user.nickname
            binding.breed.text = user.breed
            binding.description.text = user.description
        }
    }

    fun interface UserlickListener {

        fun onUserClicked(user: User)
    }
}

class UsersDiff : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.phoneNumber == newItem.phoneNumber

    override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem

}