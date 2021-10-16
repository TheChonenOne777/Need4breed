package com.chertilov.matching

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chertilov.core_api.dto.User
import com.chertilov.matching.databinding.ItemUserBinding

class UsersAdapter(private val clickListener: UserlickListener) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private var items: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context)))
            .apply {
                itemView.setOnClickListener {
                    clickListener.onUserClicked(items[adapterPosition])
                    items.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
            }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<User>) {
        items = newItems.toMutableList()
        notifyItemRangeChanged(0, newItems.size)
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
