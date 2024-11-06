package com.example.manchestercitysquadplayers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.manchestercitysquadplayers.R
import com.example.manchestercitysquadplayers.data.Player

class SquadPlayerListAdapter(private val listPlayer: ArrayList<Player>) : RecyclerView.Adapter<SquadPlayerListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_player, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPlayer.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, _, description, photo) = listPlayer[position]
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.ivPhoto.setImageResource(photo)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listPlayer[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(player: Player)
    }
}