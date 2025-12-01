package com.devsoulify.devsoulifybackend.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "artists")
data class Artist(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false, unique = true)
    val spotifyId: String,

    @Column(nullable = false)
    val name: String,

    @ManyToMany(mappedBy = "artists")
    val albums: MutableSet<Album> = mutableSetOf()
)
