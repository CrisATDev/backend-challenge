package com.devsoulify.devsoulifybackend.spotify.token

data class SpotifyTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Long
)
