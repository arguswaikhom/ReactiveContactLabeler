package com.reactivecontactlabeler.views.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reactivecontactlabeler.models.Contact
import com.reactivecontactlabeler.models.Label
import com.reactivecontactlabeler.models.ListItem
import com.reactivecontactlabeler.models.ListItemType
import com.reactivecontactlabeler.views.viewholders.ContactViewHolder
import com.reactivecontactlabeler.views.viewholders.LabelVH

class ListAdapter<T>() :
    ListAdapter<T, RecyclerView.ViewHolder>(ContactComparator()) where  T : ListItem {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ListItemType.LABEL -> LabelVH.create(parent)
            else -> ContactViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).getItemType()) {
            ListItemType.LABEL -> (holder as LabelVH).bind(getItem(position) as Label)
            else -> (holder as ContactViewHolder).bind(getItem(position) as Contact)
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).getItemType()

    class ContactComparator<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            TODO("Not yet implemented")
        }

    }
}