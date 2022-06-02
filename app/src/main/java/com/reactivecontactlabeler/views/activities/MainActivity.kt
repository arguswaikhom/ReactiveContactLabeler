package com.reactivecontactlabeler.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.reactivecontactlabeler.ContactApplication
import com.reactivecontactlabeler.R
import com.reactivecontactlabeler.data.mocks.mkContacts
import com.reactivecontactlabeler.models.Contact
import com.reactivecontactlabeler.viewmodels.ContactVMFactory
import com.reactivecontactlabeler.viewmodels.ContactViewModel

class MainActivity : AppCompatActivity() {
    private val contactVM: ContactViewModel by viewModels {
        ContactVMFactory((application as ContactApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Body() }
        supportActionBar?.title = "All Contact"
    }

    @Composable
    fun Body() {
        val contacts by contactVM.contacts.observeAsState(emptyList())

        ContactList(contacts = contacts)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = {
                    contactVM.insert(mkContacts[(0 until mkContacts.size - 1).random()])
                },
            ) { Icon(Icons.Filled.Add, "") }
        }

    }

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

    @Composable
    fun ContactItem(contact: Contact) {
        Row() {
            Image(
                modifier = Modifier
                    .size(64.dp, 64.dp)
                    .fillMaxSize()
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(contact.profileImageURL),
                contentDescription = "Contact profile image"
            )
            Column {
                Text(text = contact.name)
                Text(text = contact.phoneNo)
            }
        }
    }

    @Preview
    @Composable
    fun ContactListPreview() {
        ContactList(contacts = mkContacts)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_labels -> {
                startActivity(Intent(this, LabelActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}