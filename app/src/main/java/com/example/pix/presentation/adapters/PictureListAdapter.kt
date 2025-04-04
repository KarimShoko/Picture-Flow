package com.example.pix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.pix.databinding.PictureItemBinding
import com.example.pix.domain.entity.Picture
import com.squareup.picasso.Picasso

class PictureListAdapter() :
    ListAdapter<Picture, PictureViewHolder>(PictureDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = PictureItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val item = getItem(position)
        Picasso.get().load(item.url).into(holder.binding.imageViewPicture)
    }
}

