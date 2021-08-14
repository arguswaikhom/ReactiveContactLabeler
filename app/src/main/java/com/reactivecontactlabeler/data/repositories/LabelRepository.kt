package com.reactivecontactlabeler.data.repositories

import androidx.annotation.WorkerThread
import com.reactivecontactlabeler.data.daos.LabelDao
import com.reactivecontactlabeler.models.Label
import io.reactivex.rxjava3.core.Flowable

class LabelRepository(private val labelDao: LabelDao) {
    val allLabels: Flowable<List<Label>> = labelDao.getAll()

    @SuppressWarnings("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(label: Label) {
        labelDao.insert(label)
    }

}