package com.zulfahmi.kadefinalproject.teams

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.model.Team
import kotlinx.android.synthetic.main.teams_list.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamsAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamViewHolder(
        LayoutInflater.from(parent.context).inflate(
        R.layout.teams_list,
        parent,
        false
    ))

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int){
        holder.bindItem(teams[position], listener)
    }

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bindItem(teams: Team, listener: (Team) -> Unit){
            Picasso.get().load(teams.teamBadge).into(itemView.image)
            itemView.name.text = teams.teamName

            itemView.setOnClickListener {
                listener(teams)
            }
        }
    }
}