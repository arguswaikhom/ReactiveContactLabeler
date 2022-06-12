package com.reactivecontactlabeler.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.reactivecontactlabeler.ContactApplication
import com.reactivecontactlabeler.R
import com.reactivecontactlabeler.data.mocks.mkContacts
import com.reactivecontactlabeler.models.Contact
import com.reactivecontactlabeler.viewmodels.ContactVMFactory
import com.reactivecontactlabeler.viewmodels.ContactViewModel
import com.reactivecontactlabeler.views.components.ContactList

class MainActivity : AppCompatActivity() {
    private val contactVM: ContactViewModel by viewModels {
        ContactVMFactory((application as ContactApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Body() }
    }

    @Composable
    fun Body() {
        val contacts by contactVM.contacts.observeAsState(emptyList())
        Content(contacts = contacts)
    }

    @Composable
    fun Content(contacts: List<Contact>) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Contacts") },
                    actions = {
                        IconButton(onClick = {
                            startActivity(Intent(this@MainActivity, LabelActivity::class.java))
                        }) {
                            Icon(Icons.Filled.Menu, "Menu")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        contactVM.insert(mkContacts[(0 until mkContacts.size - 1).random()])
                    },
                ) { Icon(Icons.Filled.Add, "Add Contact") }
            }
        ) {
            ContactList(contacts = contacts)
        }
    }

    @Preview
    @Composable
    fun ContentPreview() {
        Content(contacts = mkContacts)
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