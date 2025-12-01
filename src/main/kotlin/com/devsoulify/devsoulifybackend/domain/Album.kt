package com.devsoulify.devsoulifybackend.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "albums")
data class Album(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false, unique = true)
    val spotifyId: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, name = "release_date")
    val releaseDate: String,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @ManyToMany
    @JoinTable(
        name = "album_artists",
        joinColumns = [JoinColumn(name = "album_id")],
        inverseJoinColumns = [JoinColumn(name = "artist_id")]
    )
    val artists: MutableSet<Artist> = mutableSetOf()
)
