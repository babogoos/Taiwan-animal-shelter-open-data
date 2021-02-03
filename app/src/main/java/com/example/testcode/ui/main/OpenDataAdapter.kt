package com.example.testcode.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testcode.R
import com.example.testcode.model.OpenData

/**
 * Created by dion on 2021/02/02.
 */
class OpenDataAdapter : PagingDataAdapter<OpenData, OpenDataAdapter.OpenDataViewHolder>(COMPARATOR) {
    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<OpenData>() {
            override fun areContentsTheSame(oldItem: OpenData, newItem: OpenData): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: OpenData, newItem: OpenData): Boolean =
                oldItem.animal_id == newItem.animal_id
        }
    }

    override fun onBindViewHolder(holder: OpenDataViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenDataViewHolder {
        return OpenDataViewHolder.create(parent)
    }

    class OpenDataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup): OpenDataViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_item, parent, false)
                return OpenDataViewHolder(view)
            }
        }

        private val nameTextView: TextView = view.findViewById(R.id.name_textView)
        private val statusTextView: TextView = view.findViewById(R.id.statusTextView)
        private val telTextView: TextView = view.findViewById(R.id.tel_textView)
        private val imageView: ImageView = view.findViewById(R.id.imageView)

        fun bind(openData: OpenData) {
            nameTextView.text = openData.shelter_name
            nameTextView.setOnClickListener {
                Toast.makeText(view.context, nameTextView.text, Toast.LENGTH_SHORT).show()
            }
            statusTextView.text = openData.animal_status
            telTextView.text = openData.shelter_tel

            if (openData.album_file != "") {
                Glide.with(view.context)
                    .load(openData.album_file)
                    .centerCrop()
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_foreground)
            }
        }
    }
}