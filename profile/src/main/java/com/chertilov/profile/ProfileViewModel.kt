package com.chertilov.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val interactor: ProfileInteractor) :
    ViewModel() {

    val user: LiveData<User> = interactor.getCurrentUser().flowOn(Dispatchers.IO).asLiveData()

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _breed = MutableLiveData<String>()
    val breed: LiveData<String> = _breed

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    fun onImageChanged(imageUrl: String) {
        _image.value = imageUrl
    }

    fun onNameChanged(name: String) {
        _name.value = name
    }

    fun onBreedChanged(breed: String) {
        _breed.value = breed
    }

    fun onDescriptionChanged(description: String) {
        _description.value = description
    }

    fun logout() {
        interactor.logout()
    }
}