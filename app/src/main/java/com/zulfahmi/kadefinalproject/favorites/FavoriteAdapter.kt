package com.zulfahmi.kadefinalproject.favorites

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.util.formatDate
import com.zulfahmi.kadefinalproject.util.getTimeFormat
import kotlinx.android.synthetic.main.match_list.view.*

class FavoriteAdapter(private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.match_list,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {

            itemView.date.text = favorite.matchDate?.formatDate()
            itemView.time.text = getTimeFormat(favorite.matchTime)
            itemView.club_left.text = favorite.homeTeam
            itemView.score_left.text = favorite.homeScore
            itemView.score_right.text = favorite.awayScore
            itemView.club_right.text = favorite.awayTeam

            itemView.setOnClickListener {
                listener(favorite)
            }

        }
    }
}