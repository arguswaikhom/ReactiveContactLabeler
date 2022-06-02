package com.reactivecontactlabeler.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.reactivecontactlabeler.ContactApplication
import com.reactivecontactlabeler.R
import com.reactivecontactlabeler.data.mocks.mkContacts
import com.reactivecontactlabeler.databinding.ActivityMainBinding
import com.reactivecontactlabeler.models.Contact
import com.reactivecontactlabeler.viewmodels.ContactVMFactory
import com.reactivecontactlabeler.viewmodels.ContactViewModel
import com.reactivecontactlabeler.views.adapters.ListAdapter

class MainActivity : AppCompatActivity() {
    private val contactVM: ContactViewModel by viewModels {
        ContactVMFactory((application as ContactApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "All Contact"

        val contactAdapter = ListAdapter<Contact>()
        binding.recyclerView.adapter = contactAdapter

        contactVM.contacts.observe(this) { contact ->
            contact?.let { contactAdapter.submitList(it) }
        }

        binding.addContact.setOnClickListener {
            contactVM.insert(mkContacts[(0 until mkContacts.size - 1).random()])
        }
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