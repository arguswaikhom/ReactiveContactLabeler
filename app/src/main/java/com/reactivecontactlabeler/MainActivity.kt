package com.reactivecontactlabeler

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.reactivecontactlabeler.databinding.ActivityMainBinding
import com.reactivecontactlabeler.mocks.mkContacts
import com.reactivecontactlabeler.models.Contact
import com.reactivecontactlabeler.views.ContactAdapter
import com.reactivecontactlabeler.viewmodels.ContactListViewHolder

class MainActivity : AppCompatActivity() {
    private val mViewModel: ContactListViewHolder by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactListObserver = Observer<List<Contact>> {
            val contactAdapter = ContactAdapter(it)
            binding.recyclerView.adapter = contactAdapter
        }
        mViewModel.contacts.observe(this, contactListObserver)

        binding.addContact.setOnClickListener {
            mViewModel.addContactAtTop(mkContacts[(0 until mkContacts.size - 1).random()])
        }
    }
}