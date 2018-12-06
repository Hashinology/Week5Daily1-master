package com.pixelart.week5daily1.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixelart.week5daily1.R
import com.pixelart.week5daily1.common.GlideApp
import com.pixelart.week5daily1.databinding.RecyclerviewLayoutBinding
import com.pixelart.week5daily1.model.UserData
import kotlinx.android.synthetic.main.recyclerview_layout.view.*

class RecyclerViewAdapter(val users: List<UserData>, val listener: RecyclerViewAdapter.OnItemClickedListener): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
   lateinit var context:Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        context = viewGroup.context
        val binding: RecyclerviewLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recyclerview_layout, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.apply {
            tvUserName.text = user.userName

            GlideApp.with(context)
            .load(user.avatar)
            .override(300, 200)
            .into(ivAvatar)

            tvRepositories.text = user.repos
        }

        holder.itemView.setOnClickListener {
            listener.onItemClicked(position)
        }
    }

    interface OnItemClickedListener{
        fun onItemClicked(position: Int)
    }

    class ViewHolder(binding: RecyclerviewLayoutBinding):RecyclerView.ViewHolder(binding.root)
}