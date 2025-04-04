package com.example.pix.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.pix.domain.entity.Picture

object PictureDiffCallback : DiffUtil.ItemCallback<Picture>() {

    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem == newItem
    }
}