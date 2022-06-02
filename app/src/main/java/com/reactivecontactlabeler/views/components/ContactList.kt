package com.reactivecontactlabeler.views.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.reactivecontactlabeler.data.mocks.mkContacts
import com.reactivecontactlabeler.models.Contact

@Composable
fun ContactList(contacts: List<Contact>) {
    LazyColumn() {
        items(
            items = contacts,
            itemContent = {
                ContactItem(contact = it)
            }
        )
    }
}

@Preview
@Composable
fun ContactListPreview() {
    ContactList(mkContacts)
}