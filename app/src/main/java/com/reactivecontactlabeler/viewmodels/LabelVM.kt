package com.reactivecontactlabeler.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.reactivecontactlabeler.data.repositories.LabelRepository
import com.reactivecontactlabeler.models.Label
import kotlinx.coroutines.launch

class LabelVM(private val labelRepository: LabelRepository) : ViewModel() {

    val labels = labelRepository.allLabels

    fun insert(label: Label) = viewModelScope.launch {
        labelRepository.insert(label)
    }
}

class LabelVMFactory(private val repository: LabelRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LabelVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LabelVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}