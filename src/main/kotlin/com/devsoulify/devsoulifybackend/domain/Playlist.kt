package com.devsoulify.devsoulifybackend.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "playlists")
data class Playlist(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false, unique = true, name = "spotify_id")
    val spotifyId: String,

    @Column(nullable = false)
    val name: String,

    val description: String? = null,

    @Column(name = "image_url")
    val imageUrl: String? = null
)
