package com.reactivecontactlabeler.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reactivecontactlabeler.R
import com.reactivecontactlabeler.databinding.ContactViewBinding
import com.reactivecontactlabeler.models.Contact

class ContactAdapter(private val dataset: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ContactViewBinding = ContactViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact: Contact = dataset[position]
        holder.binding.name.text = contact.name
        holder.binding.phoneNo.text = contact.phoneNo
        Glide.with(holder.binding.root.context)
            .load(contact.profileImageURL)
            .apply(RequestOptions().circleCrop())
            .into(holder.binding.profileImage)
    }

    override fun getItemCount(): Int = dataset.size
}