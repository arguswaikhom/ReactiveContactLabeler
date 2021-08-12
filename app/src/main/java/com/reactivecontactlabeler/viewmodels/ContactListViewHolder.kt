package com.reactivecontactlabeler.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reactivecontactlabeler.mocks.mkContacts
import com.reactivecontactlabeler.models.Contact

class ContactListViewHolder : ViewModel() {
    val contacts: MutableLiveData<MutableList<Contact>> = MutableLiveData(mkContacts)

    fun addContactAtTop(contact: Contact) {
        val tempContact = contacts.value
        tempContact!!.add(0, contact)
        contacts.value = tempContact
    }
}