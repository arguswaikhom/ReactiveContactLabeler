package com.reactivecontactlabeler.views.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reactivecontactlabeler.R
import com.reactivecontactlabeler.databinding.LabelBinding
import com.reactivecontactlabeler.models.Label

class LabelVH(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: LabelBinding = LabelBinding.bind(view)

    companion object {
        fun create(parent: ViewGroup): LabelVH {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.label, parent, false)
            return LabelVH(view)
        }
    }

    fun bind(label: Label) {
        binding.label.text = label.label
    }
}