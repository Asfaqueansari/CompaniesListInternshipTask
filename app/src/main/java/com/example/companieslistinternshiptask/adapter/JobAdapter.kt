package com.example.companieslistinternshiptask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.companieslistinternshiptask.R
import com.example.companieslistinternshiptask.model.Job
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_job_list_items.view.*

class JobAdapter( var jobs :List<Job>): RecyclerView.Adapter<JobAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_job_list_items,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return jobs.size
    }
    fun update(data: List<Job>){
        jobs = data
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jobs[position])
    }
    class ViewHolder(private val view: View):RecyclerView.ViewHolder(view){
        fun bind(job: Job){
            view.job_type.text = job.type
            view.company_name.text = job.company
            view.job_title.text = job.title
            Picasso.get().load(job.company_logo).into(view.companyLogo)
        }
    }
}