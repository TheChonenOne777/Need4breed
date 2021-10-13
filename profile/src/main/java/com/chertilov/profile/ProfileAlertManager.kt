package com.chertilov.profile

import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class ProfileAlertManager(private val fragment: Fragment, private val viewModel: ProfileViewModel) {

    fun showNameAlert() = showAlert(Type.NAME)
    fun showBreedAlert() = showAlert(Type.BREED)
    fun showImageAlert() = showAlert(Type.IMAGE)
    fun showDescriptionAlert() = showAlert(Type.DESCRIPTION)

    private fun showAlert(type: Type) {
        val view = fragment.layoutInflater.inflate(R.layout.layout_profile_alert, null)
        AlertDialog.Builder(fragment.requireContext())
            .setTitle(getTitle(type))
            .setMessage(getMessage(type))
            .setView(view)
            .setPositiveButton(R.string.action_save) { d, _ ->
                val text = view.findViewById<EditText>(R.id.alert_input).text.toString()
                when (type) {
                    Type.IMAGE -> viewModel.onImageChanged(text)
                    Type.NAME -> viewModel.onNameChanged(text)
                    Type.BREED -> viewModel.onBreedChanged(text)
                    Type.DESCRIPTION -> viewModel.onDescriptionChanged(text)
                }
                d.dismiss()
            }
            .setNegativeButton(R.string.action_save) { d, _ -> d.dismiss() }
            .create()
            .show()
    }

    private fun getTitle(type: Type) = when (type) {
            Type.IMAGE -> R.string.image_alert_title
            Type.NAME -> R.string.name_alert_title
            Type.BREED -> R.string.breed_alert_title
            Type.DESCRIPTION -> R.string.description_alert_title
        }

    private fun getMessage(type: Type) = when (type) {
            Type.IMAGE -> R.string.image_alert_message
            Type.NAME -> R.string.name_alert_title
            Type.BREED -> R.string.breed_alert_title
            Type.DESCRIPTION -> R.string.description_alert_title
        }

    enum class Type {
        IMAGE, NAME, BREED, DESCRIPTION
    }
}