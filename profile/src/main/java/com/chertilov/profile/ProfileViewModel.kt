package com.chertilov.profile

import androidx.lifecycle.*
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val interactor: ProfileInteractor) : ViewModel() {

    private var clicksToOpenEasterEgg = 3

    val user: LiveData<User> = interactor.getCurrentUser().flowOn(Dispatchers.IO).asLiveData()

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _breed = MutableLiveData<String>()
    val breed: LiveData<String> = _breed

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _openEasterEgg = MutableLiveData<Int>()
    val openEasterEgg: LiveData<Int> = _openEasterEgg

    fun onImageChanged(imageUrl: String) {
        _image.value = imageUrl
        viewModelScope.launch { interactor.saveImage(imageUrl) }
    }

    fun onNameChanged(name: String) {
        _name.value = name
        viewModelScope.launch { interactor.saveName(name) }
    }

    fun onBreedChanged(breed: String) {
        _breed.value = breed
        viewModelScope.launch { interactor.saveBreed(breed) }
    }

    fun onDescriptionChanged(description: String) {
        _description.value = description
        viewModelScope.launch { interactor.saveDescription(description) }
    }

    fun logout() {
        interactor.logout()
    }

    fun onDogImageClick() {
        _openEasterEgg.value = clicksToOpenEasterEgg--
    }
}