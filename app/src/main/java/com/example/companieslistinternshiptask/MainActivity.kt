package com.example.companieslistinternshiptask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.companieslistinternshiptask.adapter.JobAdapter
import com.example.companieslistinternshiptask.model.Job
import com.example.companieslistinternshiptask.model.JobRepository
import com.example.companieslistinternshiptask.viewmodel.JobViewModel
import com.example.companieslistinternshiptask.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: JobViewModel
    private lateinit var adapter: JobAdapter
    private var jobList = mutableListOf<Job>()
    companion object{
        const val TAG = "CONSOLE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()
        setUi()
        viewModel.loadData()
        listenSearch()
    }
    private fun listenSearch() {
        edt_search_list.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search(s.toString())
            }
        })
    }
    private fun search(query: String):Boolean {
        jobList.let {allJobs->
            if(query.isNotEmpty()) {
                adapter.jobs = allJobs.filter {
                    it.company!!.contains(query, true)
                }
                adapter.notifyDataSetChanged()
            }
            else{
                adapter.jobs = (allJobs)
                adapter.notifyDataSetChanged()
                return true
            }
        }
        return false
    }
    private fun setViewModel() {
        viewModel = ViewModelProvider(this,ViewModelFactory(JobRepository()))
            .get(JobViewModel::class.java)
        viewModel.jobs.observe(this,renderData)
        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
        viewModel.onMessageError.observe(this,onMessageErrorObserver)
    }
    private fun setUi() {
        adapter = JobAdapter(viewModel.jobs.value?: emptyList())
        recycler_view.layoutManager = GridLayoutManager(this,2)
        recycler_view.adapter = adapter
    }
    //observe
    private val renderData = Observer<List<Job>>{data->
        jobList = data.toMutableList()
        adapter.update(jobList)
    }
    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG,"data updated $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }
    private val onMessageErrorObserver = Observer<Any> {
        Log.v(TAG,"Error m                                                                                                                                                   nn                                                                                                                                                                                                             b                  $it")
    }
}
