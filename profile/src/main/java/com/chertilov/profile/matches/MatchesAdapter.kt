package com.chertilov.profile.matches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chertilov.core_api.dto.User
import com.chertilov.profile.databinding.ItemMatchBinding


class MatchesAdapter(private val clickListener: UserClickListener) : ListAdapter<User, MatchesAdapter.UserViewHolder>(UsersDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(ItemMatchBinding.inflate(LayoutInflater.from(parent.context)))
            .apply { itemView.setOnClickListener { clickListener.onUserClicked(getItem(adapterPosition)) } }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserViewHolder(private val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            Glide.with(binding.root.context)
                .load(user.image)
                .into(binding.image)
            binding.name.text = user.nickname
            binding.breed.text = user.breed
        }
    }

    fun interface UserClickListener {

        fun onUserClicked(user: User)
    }
}

class UsersDiff : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.phoneNumber == newItem.phoneNumber

    override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem

}