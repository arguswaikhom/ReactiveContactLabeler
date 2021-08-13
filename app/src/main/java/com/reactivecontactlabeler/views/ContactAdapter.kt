package com.reactivecontactlabeler.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reactivecontactlabeler.R
import com.reactivecontactlabeler.databinding.ContactViewBinding
import com.reactivecontactlabeler.models.Contact

class ContactAdapter() :
    ListAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactComparator()) {

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ContactViewBinding = ContactViewBinding.bind(view)

        companion object {
            fun create(parent: ViewGroup): ContactViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.contact_view, parent, false)
                return ContactViewHolder(view)
            }
        }

        fun bind(contact: Contact) {
            binding.name.text = contact.name
            binding.phoneNo.text = contact.phoneNo
            Glide.with(binding.root.context)
                .load(contact.profileImageURL)
                .apply(RequestOptions().circleCrop())
                .into(binding.profileImage)
        }
    }

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