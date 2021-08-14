package com.reactivecontactlabeler.views.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reactivecontactlabeler.R
import com.reactivecontactlabeler.databinding.ContactViewBinding
import com.reactivecontactlabeler.models.Contact

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