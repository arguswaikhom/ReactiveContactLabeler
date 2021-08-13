package com.reactivecontactlabeler

import android.app.Application
import com.reactivecontactlabeler.repositories.ContactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ContactApplication : Application() {
    val applicationScope: CoroutineScope = CoroutineScope(SupervisorJob())

    val database by lazy { ContactRoomDB.getDatabase(this, applicationScope) }
    val repository by lazy { ContactRepository(database.contactDao()) }
}