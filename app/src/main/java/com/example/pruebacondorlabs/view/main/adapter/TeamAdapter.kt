package com.example.pruebacondorlabs.view.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebacondorlabs.R
import com.example.pruebacondorlabs.db.model.Team
import com.example.pruebacondorlabs.util.TEAM
import com.example.pruebacondorlabs.view.detail.DetailActivity

class TeamAdapter(
    private val context: Context,
    private val listTeams: ArrayList<Team>?
) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    class ViewHolder(itemViewGroup: ViewGroup) : RecyclerView.ViewHolder(itemViewGroup)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_team, parent, false) as ConstraintLayout
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(listTeams!![position].strTeamBadge)
            .placeholder(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.placeholder,
                    null
                )
            ).fitCenter()
            .into(holder.itemView.findViewById(R.id.ivTeamBadge))

        val name = "${context.getString(R.string.name)}: ${listTeams[position].strTeam}"
        val stadium = "${context.getString(R.string.stadium)}: ${listTeams[position].strStadium}"

        holder.itemView.findViewById<TextView>(R.id.tvNameTeam).text = name
        holder.itemView.findViewById<TextView>(R.id.tvStadiumTeam).text = stadium
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(TEAM, listTeams[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = listTeams?.size!!
}