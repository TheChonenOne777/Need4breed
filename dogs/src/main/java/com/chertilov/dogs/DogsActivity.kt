package com.chertilov.dogs

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chertilov.core_api.dto.Dog
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.dogs.di.DogsComponent
import com.chertilov.utils.bind
import com.chertilov.utils.start
import javax.inject.Inject

class DogsActivity : AppCompatActivity() {

    private val recycler by bind<RecyclerView>(R.id.dogs_recycler)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DogsViewModel by viewModels { viewModelFactory }

    private val adapter by lazy {
        DogsAdapter(object : DogsAdapter.DogClickListener {
            override fun onDogClicked(dog: Dog) {
                start<DogDetailsActivity> { putExtra(DogDetailsActivity.DOG_IMG_EXTRA, dog.image) }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DogsComponent.create((application as AppWithFacade).getFacade()).inject(this)
        setContentView(R.layout.activity_dogs)
        recycler.adapter = adapter
        recycler.layoutManager =
            GridLayoutManager(this, NUMBER_OF_COLUMNS, RecyclerView.VERTICAL, false)

        viewModel.dogs.observe(this) { setDogs(it) }
        viewModel.onActivityCreated()
    }

    private fun setDogs(dogs: List<Dog>) {
        adapter.setItems(dogs)
    }

    companion object {
        const val NUMBER_OF_COLUMNS = 3
    }
}