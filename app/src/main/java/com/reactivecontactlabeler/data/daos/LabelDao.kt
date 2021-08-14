package com.reactivecontactlabeler.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reactivecontactlabeler.models.Label
import io.reactivex.rxjava3.core.Flowable

@Dao
interface LabelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(label: Label)

    @Query("SELECT * FROM label")
    fun getAll(): Flowable<List<Label>>

    @Query("DELETE FROM label")
    suspend fun deleteAll()
}