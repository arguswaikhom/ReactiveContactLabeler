package com.reactivecontactlabeler

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.reactivecontactlabeler.data.daos.ContactDao
import com.reactivecontactlabeler.models.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Contact::class], version = 1)
abstract class ContactRoomDB : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactRoomDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ContactRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactRoomDB::class.java,
                    "contact_database",
                ).addCallback(ContactDBCallbacks(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class ContactDBCallbacks(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    Log.d("db", "deletion")
                    database.contactDao().deleteAll()
                }
            }
        }
    }
}