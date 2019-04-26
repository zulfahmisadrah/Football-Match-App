package com.zulfahmi.kadefinalproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zulfahmi.kadefinalproject.R
import kotlinx.android.synthetic.main.match_list.view.*
import com.zulfahmi.kadefinalproject.model.Match
import com.zulfahmi.kadefinalproject.util.formatDate
import com.zulfahmi.kadefinalproject.util.getTimeFormat

class MatchListAdapter(private val match: List<Match>, private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<MatchListAdapter.MatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
        MatchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.match_list,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(match[position], listener)
    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(match: Match, listener: (Match) -> Unit) {

            itemView.date.text = match.eventDate?.formatDate()
            itemView.time.text = getTimeFormat(match.eventTime)
            itemView.club_left.text = match.homeTeam
            itemView.score_left.text = match.homeScore
            itemView.score_right.text = match.awayScore
            itemView.club_right.text = match.awayTeam

            itemView.setOnClickListener {
                listener(match)
            }

        }
    }
}