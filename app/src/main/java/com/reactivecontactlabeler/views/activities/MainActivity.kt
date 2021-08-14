package com.reactivecontactlabeler.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.reactivecontactlabeler.ContactApplication
import com.reactivecontactlabeler.data.mocks.mkContacts
import com.reactivecontactlabeler.databinding.ActivityMainBinding
import com.reactivecontactlabeler.viewmodels.ContactVMFactory
import com.reactivecontactlabeler.viewmodels.ContactViewModel
import com.reactivecontactlabeler.views.adapters.ContactAdapter

class MainActivity : AppCompatActivity() {
    private val contactVM: ContactViewModel by viewModels {
        ContactVMFactory((application as ContactApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactAdapter = ContactAdapter()
        binding.recyclerView.adapter = contactAdapter

        contactVM.contacts.observe(this, { contact ->
            contact?.let { contactAdapter.submitList(it) }
        })

        binding.addContact.setOnClickListener {
            contactVM.insert(mkContacts[(0 until mkContacts.size - 1).random()])
        }
    }
}