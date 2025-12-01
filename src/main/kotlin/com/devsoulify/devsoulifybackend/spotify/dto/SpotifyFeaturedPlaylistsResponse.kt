package com.devsoulify.devsoulifybackend.spotify.dto

data class SpotifyFeaturedPlaylistsResponse(
    val playlists: Playlists
)

data class Playlists(
    val items: List<Playlist>
)

data class Playlist(
    val id: String,
    val name: String,
    val description: String,
    val images: List<Image>
)
