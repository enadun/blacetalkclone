package com.enadun.blacetalk.adapters

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enadun.blacetalk.R
import com.enadun.blacetalk.model.Data
import com.google.android.material.card.MaterialCardView

class MainAdapter(private val data: Data) : RecyclerView.Adapter<ListItemView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemView {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)
        return ListItemView(parent.context, view)
    }

    override fun onBindViewHolder(holder: ListItemView, position: Int) {
        holder.bindView(position, data)
    }

    override fun getItemCount(): Int {
        return 3
    }
}

class ListItemView(private val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val sectionTitle: TextView = itemView.findViewById(R.id.section_title)
    private val mainRecyclerView: RecyclerView = itemView.findViewById(R.id.items_recycler_view)
    fun bindView(index: Int, data: Data) {
        when (index) {
            0 -> {
                sectionTitle.text = context.getText(R.string.recent_episodes)
                mainRecyclerView.adapter = EpisodesAdapter(data.episodes)
            }
            1 -> {
                sectionTitle.text = context.getText(R.string.top_channels)
                mainRecyclerView.adapter = ChannelsAdapter(data.channels)

            }
            2 -> {
                sectionTitle.text = context.getText(R.string.speakers)
                mainRecyclerView.adapter = SpeakersAdapter(data.speakers)
            }
        }
        if (index % 2 == 1) {
            itemView.background = AppCompatResources.getDrawable(context, R.color.white)
        }
        mainRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}

class EpisodesAdapter(private val episodes: List<Data.Episode>) :
    RecyclerView.Adapter<EpisodesView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesView {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_episodes, parent, false)
        return EpisodesView(view)
    }

    override fun onBindViewHolder(holder: EpisodesView, position: Int) {
        holder.bindView(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.count()
    }
}

class EpisodesView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.episode_title)
    private val clubTextView: TextView = itemView.findViewById(R.id.club_name)
    private val dateTextView: TextView = itemView.findViewById(R.id.date_text)

    fun bindView(episode: Data.Episode) {
        val spanText = SpannableString(episode.episode + " / " + episode.title)
        val span = StyleSpan(Typeface.BOLD)
        spanText.setSpan(span, 5, spanText.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        titleTextView.text = spanText
        clubTextView.text = episode.club.uppercase()
        dateTextView.text = episode.date.uppercase()
    }
}

class ChannelsAdapter(private val channels: List<Data.Channel>) :
    RecyclerView.Adapter<OtherView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_other, parent, false)
        return OtherView(parent.context, view)
    }

    override fun onBindViewHolder(holder: OtherView, position: Int) {
        val ch = channels[position]
        holder.bindView(true, ch.name, ch.episodes, ch.image)
    }

    override fun getItemCount(): Int {
        return channels.count()
    }
}

class SpeakersAdapter(private val speakers: List<Data.Speaker>) :
    RecyclerView.Adapter<OtherView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_other, parent, false)
        return OtherView(parent.context, view)
    }

    override fun onBindViewHolder(holder: OtherView, position: Int) {
        val ch = speakers[position]
        holder.bindView(false, ch.name, ch.episodes, ch.image)
    }

    override fun getItemCount(): Int {
        return speakers.count()
    }
}

class OtherView(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameView: TextView = itemView.findViewById(R.id.name_text)
    private val episodesView: TextView = itemView.findViewById(R.id.episodes_Text)
    private val imageView: ImageView = itemView.findViewById(R.id.image_view)
    private val cardView: MaterialCardView = itemView.findViewById(R.id.card_view)

    fun bindView(isOdd: Boolean, name: String, count: Int, imageName: String) {
        nameView.text = name
        episodesView.text = "$count " + context.getString(R.string.episodes).uppercase()
        val resID: Int = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        imageView.setImageResource(resID)

        if (isOdd) {
            itemView.background = AppCompatResources.getDrawable(context, R.color.white)
            cardView.background = AppCompatResources.getDrawable(context, R.color.background_gray)
        }

    }
}















