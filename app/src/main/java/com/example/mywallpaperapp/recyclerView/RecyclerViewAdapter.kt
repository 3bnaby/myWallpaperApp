package com.example.mywallpaperapp.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.example.mywallpaperapp.R
import com.example.mywallpaperapp.databinding.ItemRecyclerViewBinding
import com.example.mywallpaperapp.model.Data
import com.example.mywallpaperapp.utils.BlurHashDecoder

class RecyclerViewAdapter (private val listener: WallInteractionListener)
    : PagingDataAdapter<Data,RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()){
    inner class MyViewHolder(view:View) :RecyclerView.ViewHolder(view) {
        private val binding = ItemRecyclerViewBinding.bind(view)

        fun bind(data: Data) {

            val blurHashAsDrawable = BlurHashDecoder.blurHashBitmap(itemView.resources,data)

            Glide.with(itemView.context)
                .asBitmap()
                .load(data.smallImageUrl)
                .centerCrop()
                .transition(BitmapTransitionOptions.withCrossFade(80))
                .error(blurHashAsDrawable)
                .placeholder(blurHashAsDrawable)
                .into(binding.imageView)

            itemView.setOnClickListener{
                listener.onClickItem(data ,it)

            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent,false)
        return MyViewHolder(inflater)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.blurHash == newItem.blurHash
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
}
