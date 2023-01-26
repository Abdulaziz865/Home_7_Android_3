package com.example.home_7_android_3.hilt.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.home_7_android_3.databinding.ItemListBinding
import com.example.home_7_android_3.hilt.data.models.HomeModel

class HomeAdapter : ListAdapter<HomeModel, HomeAdapter.ItemViewHolder>(diffUtil) {
    inner class ItemViewHolder(private val binding: ItemListBinding) : ViewHolder(binding.root) {
        fun onBind(item: HomeModel) {
            Glide.with(binding.imageRam.context)
                .load(item.url + ".png")
                .into(binding.imageRam)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HomeModel>() {
            override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}