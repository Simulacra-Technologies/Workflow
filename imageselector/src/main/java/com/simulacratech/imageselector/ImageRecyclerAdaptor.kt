package com.simulacratech.imageselector

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ImageRecyclerAdaptor(
    private val arrayListUri: ArrayList<Uri>,
    private val imageInterface: ImageInterface
) :
    RecyclerView.Adapter<ImageRecyclerAdaptor.RecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = arrayListUri[position]
        holder.imageView.setImageURI(currentItem)
        holder.button.setOnClickListener {
            imageInterface.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return arrayListUri.size
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_hazard_image_item)
        val button: TextView = itemView.findViewById(R.id.tv_hazard_image_item_delete)
    }

}
