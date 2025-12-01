package com.devsoulify.devsoulifybackend.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "album_artists")
data class AlbumArtist(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    val album: Album,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    val artist: Artist
)
