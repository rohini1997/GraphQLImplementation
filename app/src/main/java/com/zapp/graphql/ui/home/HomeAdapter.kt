package com.zapp.graphql.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.zapp.graphql.R
import com.zapp.graphql.data.dto.Photos
import com.zapp.graphql.utility.loadUrl
import com.zapp.graphql.databinding.PhotoItemBinding

class HomeAdapter(
    private var photoUiData: List<Photos>, private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {


    inner class MyViewHolder(private val viewBinding: PhotoItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        val ivDelete: ImageButton = itemView.findViewById(R.id.ivDelete)
        val ivEdit: ImageButton = itemView.findViewById(R.id.ivEdit)

        fun bindItems(characterUiData: Photos) {
            viewBinding.ivPhoto.loadUrl(characterUiData.url)
            viewBinding.txtPhoto.text = characterUiData.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        val binding =
            PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(photoUiData[position])
        holder.ivDelete.setOnClickListener {
            listener.onDelete(photoUiData[position])
        }
        holder.ivEdit.setOnClickListener {
            listener.onEdit(photoUiData[position])
        }

    }

    override fun getItemCount(): Int {
        return photoUiData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(characterUiData: List<Photos>) {
        this.photoUiData = characterUiData
        notifyDataSetChanged()

    }

    interface OnItemClickListener {
        fun onDelete(photos: Photos)
        fun onEdit(photos: Photos)
        // Add more methods for other actions if needed
    }
}
