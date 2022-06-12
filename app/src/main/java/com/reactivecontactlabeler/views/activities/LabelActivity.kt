package com.reactivecontactlabeler.views.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.reactivecontactlabeler.ContactApplication
import com.reactivecontactlabeler.databinding.ActivityLabelBinding
import com.reactivecontactlabeler.models.Label
import com.reactivecontactlabeler.viewmodels.LabelVM
import com.reactivecontactlabeler.viewmodels.LabelVMFactory
import com.reactivecontactlabeler.views.adapters.ListAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LabelActivity : AppCompatActivity() {
    private lateinit var disposable: Disposable
    private val labelVM: LabelVM by viewModels {
        LabelVMFactory((application as ContactApplication).labelRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLabelBinding = ActivityLabelBinding.inflate(layoutInflater)
        // setContentView(binding.root)
        setContent { Body() }
        supportActionBar?.title = "Labels"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val labelAdapter = ListAdapter<Label>()
        binding.labelRecyclerView.adapter = labelAdapter

        disposable = labelVM.labels.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { labels -> labelAdapter.submitList(labels) }

        binding.addLabelBtn.setOnClickListener {
            val newLabel = binding.newLabelEt.text.toString()
            if (newLabel.isEmpty()) return@setOnClickListener
            labelVM.insert(Label(newLabel))
            binding.newLabelEt.text.clear()
        }
    }

    @Composable
    private fun Body() {
        var label by rememberSaveable { mutableStateOf("") }
        Content(label = label, onUpdateLabel = { label = it })
    }

    @Composable
    fun Content(label: String, onUpdateLabel: (String) -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Label") })
            },
            bottomBar = {
                Row {
                    TextField(
                        value = label,
                        onValueChange = onUpdateLabel
                    )
                }
            }
        ) {
            LabelList()
        }
    }

    @Composable
    private fun LabelList() {
    }

    @Preview
    @Composable
    fun ContentPreview() {
        Content(label = "Boiling Water!!", onUpdateLabel = {})
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::disposable.isInitialized) disposable.dispose()
    }
}