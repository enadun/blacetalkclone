package com.enadun.blacetalk.model

import org.json.JSONObject

class Data(jsonObject: JSONObject) {
    var episodes: MutableList<Episode> = mutableListOf()
    var channels: MutableList<Channel> = mutableListOf()
    var speakers: MutableList<Speaker> = mutableListOf()

    init {
        val data = jsonObject.getJSONObject("data")
        val episodesItems = data.getJSONArray("episodes")
        for (i in 0 until episodesItems.length()) {
            val episode = Episode(episodesItems.getJSONObject(i))
            episodes.add(episode)
        }
        val channelsItems = data.getJSONArray("channels")
        for (i in 0 until channelsItems.length()) {
            val channel = Channel(channelsItems.getJSONObject(i))
            channels.add(channel)
        }
        val speakersItems = data.getJSONArray("speakers")
        for (i in 0 until speakersItems.length()) {
            val speaker = Speaker(speakersItems.getJSONObject(i))
            speakers.add(speaker)
        }
    }

    class Episode(jsonObject: JSONObject) {
        val episode: String = jsonObject.getString("episode")
        val title: String = jsonObject.getString("title")
        val club: String = jsonObject.getString("club")
        val date: String = jsonObject.getString("date")
    }

    class Channel(jsonObject: JSONObject) {
        val name: String = jsonObject.getString("name")
        val episodes = jsonObject.getInt("episodes")
        val image: String = jsonObject.getString("image")
    }

    class Speaker(jsonObject: JSONObject) {
        val name: String = jsonObject.getString("name")
        val episodes = jsonObject.getInt("episodes")
        val image: String = jsonObject.getString("image")
    }
}