package com.reactivecontactlabeler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reactivecontactlabeler.databinding.ActivityMainBinding
import com.reactivecontactlabeler.mocks.mkContacts
import com.reactivecontactlabeler.view.ContactAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactAdapter = ContactAdapter(mkContacts)
        binding.recyclerView.adapter = contactAdapter
    }
}