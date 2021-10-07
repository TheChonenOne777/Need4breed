package com.chertilov.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chertilov.core_api.dto.Dog
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.dogs.di.DogsComponent
import com.chertilov.dogs.di.EagerTrigger
import com.chertilov.utils.bind
import com.chertilov.utils.unsafeLazy
import javax.inject.Inject

class DogsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var eagerTrigger: EagerTrigger

//    private val viewModel: DogsViewModel by viewModels { viewModelFactory }
    private lateinit var viewModel: DogsViewModel


    private val adapter by unsafeLazy {
        DogsAdapter(object : DogsAdapter.DogClickListener {
            override fun onDogClicked(dog: Dog) {
                findNavController().navigate(DogsFragmentDirections.actionDogFragmentToDogDetailsFragment(dog.image))
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DogsComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DogsViewModel::class.java)
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
        val recycler by view.bind<RecyclerView>(R.id.dogs_recycler)
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