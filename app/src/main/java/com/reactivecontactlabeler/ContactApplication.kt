package com.reactivecontactlabeler

import android.app.Application
import com.reactivecontactlabeler.data.repositories.ContactRepository
import com.reactivecontactlabeler.data.repositories.LabelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ContactApplication : Application() {
    val applicationScope: CoroutineScope = CoroutineScope(SupervisorJob())

    val database by lazy { ContactRoomDB.getDatabase(this, applicationScope) }
    val repository by lazy { ContactRepository(database.contactDao()) }
    val labelRepository by lazy { LabelRepository(database.labelDao()) }
}