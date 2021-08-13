package com.reactivecontactlabeler.utils

import androidx.recyclerview.widget.DiffUtil
import com.reactivecontactlabeler.models.Contact

class ContactComparator : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.name == newItem.name
                && oldItem.phoneNo == newItem.phoneNo
                && oldItem.profileImageURL == newItem.profileImageURL
    }

    fun isNameSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.name == newItem.name
    }
}