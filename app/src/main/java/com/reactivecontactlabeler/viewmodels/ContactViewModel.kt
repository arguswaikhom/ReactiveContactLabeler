package com.reactivecontactlabeler.viewmodels

import androidx.lifecycle.*
import com.reactivecontactlabeler.models.Contact
import com.reactivecontactlabeler.repositories.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(private val contactRepository: ContactRepository) : ViewModel() {
    val contacts: LiveData<List<Contact>> = contactRepository.allContact.asLiveData()

    fun insert(contact: Contact) = viewModelScope.launch {
        contactRepository.insert(contact)
    }
}

class ContactVMFactory(private val contactRepository: ContactRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(contactRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}