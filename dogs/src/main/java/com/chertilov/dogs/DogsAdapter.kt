package com.chertilov.dogs

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chertilov.core_api.dto.Dog

class DogsAdapter(private val listener: DogClickListener) :
    RecyclerView.Adapter<DogsAdapter.DogViewHolder>() {

    private var items = listOf<Dog>()

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
        }).apply { itemView.setOnClickListener { listener.onDogClicked(getItem(adapterPosition)) } }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(getItem(position).image)
            .into(holder.itemView as ImageView)
    }

    override fun getItemCount() = items.size

    fun setItems(newDogs: List<Dog>) {
        items = newDogs
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = items[position]

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface DogClickListener {

        fun onDogClicked(dog: Dog)
    }
}
