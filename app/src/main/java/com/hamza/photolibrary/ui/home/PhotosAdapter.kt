package com.hamza.photolibrary.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hamza.photolibrary.R
import com.hamza.photolibrary.databinding.PhotoItemLayoutBinding
import com.hamza.photolibrary.data.model.Photo

class PhotosAdapter(val onPhotoSelected: (photo: Photo, position: Int) -> Unit): RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    private val photoItems: ArrayList<Photo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var binding = PhotoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoItems[position], position)
    }

    override fun getItemCount() = photoItems.size

    fun updateItems(photosList: List<Photo>) {
        photoItems.clear()
        photoItems.addAll(photosList)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(val itemBinding: PhotoItemLayoutBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(photo: Photo, position: Int) {
            itemBinding.apply {
                imgPhoto.load(photo.urls.thumb) {
                    placeholder(R.color.color_box_background)
                    crossfade(true)
                }

                cardPhoto.setOnClickListener {
                    onPhotoSelected(photo, position)
                }

            }
        }
    }
}