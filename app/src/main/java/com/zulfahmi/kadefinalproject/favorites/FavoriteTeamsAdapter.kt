package com.zulfahmi.kadefinalproject.favorites

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.zulfahmi.kadefinalproject.R
import kotlinx.android.synthetic.main.teams_list.view.*

class FavoriteTeamsAdapter(private val favorite: List<FavoriteTeams>, private val listener: (FavoriteTeams) -> Unit)
    : RecyclerView.Adapter<FavoriteTeamsAdapter.FavoriteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.teams_list,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(favorite: FavoriteTeams, listener: (FavoriteTeams) -> Unit) {

            Picasso.get().load(favorite.teamBadge).into(itemView.image)
            itemView.name.text = favorite.teamName

            itemView.setOnClickListener {
                listener(favorite)
            }

        }
    }
}