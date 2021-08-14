package com.reactivecontactlabeler.views.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.reactivecontactlabeler.models.Contact
import com.reactivecontactlabeler.views.viewholders.ContactViewHolder

class ContactAdapter() :
    ListAdapter<Contact, ContactViewHolder>(ContactComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact: Contact = getItem(position)
        holder.bind(contact)
    }

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
}