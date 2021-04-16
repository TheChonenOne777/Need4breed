package com.chertilov.need4breed.dogs

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chertilov.need4breed.R
import com.chertilov.need4breed.ViewModelFactoryInjector
import com.chertilov.need4breed.utils.bind
import com.chertilov.need4breed.utils.start

class DogsActivity : AppCompatActivity() {

    private val recycler by bind<RecyclerView>(R.id.dogs_recycler)

    private val viewModel: DogsViewModel by viewModels {
        ViewModelFactoryInjector.provideDogsViewModel()
    }

    private val adapter by lazy {
        DogsAdapter(object : DogsAdapter.DogClickListener {
            override fun onDogClicked(dog: String) {
                start<DogDetailsActivity> { putExtra(DogDetailsActivity.DOG_IMG_EXTRA, dog) }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(this, NUMBER_OF_COLUMNS, RecyclerView.VERTICAL, false)

        viewModel.dogs.observe(this) { setDogs(it) }
        viewModel.onActivityCreated()
    }

    private fun setDogs(dogs: List<String>) {
        adapter.setItems(dogs)
    }

    companion object {
        const val NUMBER_OF_COLUMNS = 3
    }
}