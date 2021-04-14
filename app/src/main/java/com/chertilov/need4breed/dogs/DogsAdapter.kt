package com.chertilov.need4breed.dogs

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DogsAdapter : RecyclerView.Adapter<DogsAdapter.DogViewHolder>() {

    private var items = listOf<String>()

    private var itemSize = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DogViewHolder(ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            if (itemSize == 0) itemSize = parent.width / DogsActivity.NUMBER_OF_COLUMNS
            layoutParams.width = itemSize
            layoutParams.height = itemSize
            scaleType = ImageView.ScaleType.CENTER_CROP
        })

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(getItem(position))
            .into(holder.itemView as ImageView)
    }

    override fun getItemCount() = items.size

    fun setItems(newDogs: List<String>) {
        items = newDogs
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = items[position]

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
