package com.example.pruebacondorlabs.view.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebacondorlabs.R
import com.example.pruebacondorlabs.db.model.Result
import com.example.pruebacondorlabs.view.main.adapter.TeamAdapter
import java.text.SimpleDateFormat
import java.util.*

class EventAdapter(
    private val context: Context,
    private val listResults: ArrayList<Result>?
) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private val simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())
    private val simpleDateFormat2 = SimpleDateFormat("MMMM dd, yyyy\nhh:mm a", Locale.getDefault())

    class ViewHolder(itemViewGroup: ViewGroup) : RecyclerView.ViewHolder(itemViewGroup)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHolder {
        return TeamAdapter.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_result, parent, false) as ConstraintLayout
        )
    }

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) {
        val imageViewThumb = holder.itemView.findViewById<ImageView>(R.id.tvThumbResult)
        val date1 = simpleDateFormat1.parse(listResults!![position].strTimestamp!!)
        val event = "${context.getString(R.string.match)}: ${listResults[position].strEvent}"
        val venue = "${context.getString(R.string.stadium)}: ${listResults[position].strVenue}"
        val league = "${context.getString(R.string.event)}: ${listResults[position].strLeague}"
        if (listResults[position].strThumb == null || listResults[position].strThumb?.isEmpty()!!)
            imageViewThumb.visibility = View.GONE
        else Glide.with(context)
            .load(listResults[position].strThumb)
            .fitCenter()
            .into(holder.itemView.findViewById(R.id.tvThumbResult))

        holder.itemView.findViewById<TextView>(R.id.tvEventResult).text = event
        holder.itemView.findViewById<TextView>(R.id.tvVenueResult).text = venue
        holder.itemView.findViewById<TextView>(R.id.tvLeagueResult).text = league
        holder.itemView.findViewById<TextView>(R.id.tvTimestamp).text =
            simpleDateFormat2.format(date1!!)
    }

    override fun getItemCount() = listResults?.size!!
}