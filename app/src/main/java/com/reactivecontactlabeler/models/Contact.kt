package com.reactivecontactlabeler.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
class Contact(
    val name: String,
    @ColumnInfo(name = "phone_no") val phoneNo: String,
    @ColumnInfo(name = "profile_image_url") val profileImageURL: String,
) : ListItem {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    override fun getItemType(): Int = ListItemType.CONTACT
}