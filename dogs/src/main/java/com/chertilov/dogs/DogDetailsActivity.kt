package com.chertilov.dogs

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.dogs.di.DogsComponent
import com.chertilov.utils.bind

class DogDetailsActivity : AppCompatActivity() {

    private val dogImg by bind<ImageView>(R.id.img_dog)
    private val toolbar by bind<Toolbar>(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DogsComponent.create((application as AppWithFacade).getFacade()).inject(this)
        setContentView(R.layout.activity_dog_details)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        intent.getStringExtra(DOG_IMG_EXTRA)?.let {
            toolbar.title = it.substringAfter("breeds/", "").substringBefore("/")
            Glide.with(this).load(it).into(dogImg)
        }
    }

    companion object {
        const val DOG_IMG_EXTRA = "DOG_IMG_EXTRA"
    }
}