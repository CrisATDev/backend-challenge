package com.devsoulify.devsoulifybackend.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "categories")
data class Category(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false, unique = true, name = "spotify_id")
    val spotifyId: String,

    @Column(nullable = false)
    val name: String,

    @Column(name = "icon_url")
    val iconUrl: String? = null
)
