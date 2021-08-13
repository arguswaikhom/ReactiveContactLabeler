package com.reactivecontactlabeler.repositories

import androidx.annotation.WorkerThread
import com.reactivecontactlabeler.daos.ContactDao
import com.reactivecontactlabeler.models.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {

    val allContact: Flow<List<Contact>> = contactDao.getAllContact()

    @SuppressWarnings("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }
}