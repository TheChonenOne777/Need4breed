package com.chertilov.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.chertilov.core_api.base.ToolbarView
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.dogs.di.DogsComponent
import com.chertilov.utils.bind

class DogDetailsFragment : Fragment() {

    private val dogImg by bind<ImageView>(R.id.img_dog)
    private val toolbar by bind<ToolbarView>(R.id.appbar)

    private val dogImageArg: DogDetailsFragmentArgs by navArgs()

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
        return inflater.inflate(R.layout.fragment_dog_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogImageArg.image.let {
            toolbar.setTitle(it.substringAfter("breeds/", "").substringBefore("/"))
            Glide.with(this).load(it).into(dogImg)
        }
    }
}