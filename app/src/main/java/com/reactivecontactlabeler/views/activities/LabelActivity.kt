package com.reactivecontactlabeler.views.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
        setContentView(binding.root)
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