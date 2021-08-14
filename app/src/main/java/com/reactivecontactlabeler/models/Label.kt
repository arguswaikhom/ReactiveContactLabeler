package com.reactivecontactlabeler.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "label")
class Label(@PrimaryKey val label: String) : ListItem {
    override fun getItemType(): Int = ListItemType.LABEL
}