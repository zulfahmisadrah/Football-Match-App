package com.zulfahmi.kadefinalproject.teams.details.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.model.Player
import kotlinx.android.synthetic.main.players_list.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayersAdapter(private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayersAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.players_list, parent, false))
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bindItem(player: Player, listener: (Player) -> Unit){
            Picasso.get().load(player.playerBadge).into(itemView.image)
            itemView.name.text = player.playerName
            itemView.position.text = player.playerPosition

            itemView.onClick {
                listener(player)
            }
        }
    }

}