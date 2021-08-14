package com.reactivecontactlabeler.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reactivecontactlabeler.models.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    fun getAllContact(): Flow<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Query("DELETE FROM contact")
    suspend fun deleteAll()
}