package com.example.caesarappgithub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caesarappgithub.Itemgithub.Api.FollowResponseItem
import com.example.caesarappgithub.databinding.ListFollowBinding

class FollowListAdapter(private val list: List<FollowResponseItem>) :
    RecyclerView.Adapter<FollowListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ListFollowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListFollowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.binding.uiName.text = data.login
        Glide.with(holder.itemView.context)
            .load(data.avatarUrl)
            .into(holder.binding.fotoFollow)
    }

    override fun getItemCount(): Int = list.size
}