package com.devsoulify.devsoulifybackend.spotify.dto

data class SpotifyNewReleasesResponse(
    val albums: Albums
)

data class Albums(
    val items: List<Album>
)

data class Album(
    val id: String,
    val name: String,
    val release_date: String,
    val images: List<Image>,
    val artists: List<Artist>
)

data class Artist(
    val id: String,
    val name: String
)

data class Image(
    val url: String,
    val height: Int?,
    val width: Int?
)
