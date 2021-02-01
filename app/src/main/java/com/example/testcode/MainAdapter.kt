package com.example.testcode

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide


class MainAdapter(var items: List<OpenData>, var context: Context) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val TAG = MainAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = items[position].shelter_name
        holder.nameTextView.setOnClickListener {
            Toast.makeText(context, holder.nameTextView.text, Toast.LENGTH_SHORT).show()
        }
        holder.statusTextView.text = items[position].animal_status
        holder.telTextView.text = items[position].shelter_tel

        if (items[position].album_file != "") {
            Glide.with(context)
                .load(items[position].album_file)
                .centerCrop()
                .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.name_textView)
        val statusTextView = view.findViewById<TextView>(R.id.statusTextView)
        val telTextView = view.findViewById<TextView>(R.id.tel_textView)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
    }
}