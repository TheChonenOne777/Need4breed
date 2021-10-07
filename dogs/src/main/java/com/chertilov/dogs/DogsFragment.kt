package com.chertilov.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chertilov.core_api.dto.Dog
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.dogs.di.DogsComponent
import com.chertilov.utils.bind
import javax.inject.Inject

class DogsFragment : Fragment() {

    private val recycler by bind<RecyclerView>(R.id.dogs_recycler)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DogsViewModel by viewModels { viewModelFactory }

    private val adapter by lazy {
        DogsAdapter(object : DogsAdapter.DogClickListener {
            override fun onDogClicked(dog: Dog) {
//                requireActivity().start<DogDetailsFragment> { putExtra(DogDetailsFragment.DOG_IMG_EXTRA, dog.image) }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DogsComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dogs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.adapter = adapter
        recycler.layoutManager =
            GridLayoutManager(requireContext(), NUMBER_OF_COLUMNS, RecyclerView.VERTICAL, false)

        viewModel.dogs.observe(viewLifecycleOwner) { setDogs(it) }
        viewModel.onActivityCreated()
    }

    private fun setDogs(dogs: List<Dog>) {
        adapter.setItems(dogs)
    }

    companion object {
        const val NUMBER_OF_COLUMNS = 3
    }
}