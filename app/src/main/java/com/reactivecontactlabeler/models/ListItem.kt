package com.reactivecontactlabeler.models

interface ListItem {
    fun getItemType(): Int
}

object ListItemType {
    const val CONTACT: Int = 1
    const val LABEL: Int = 2
}

