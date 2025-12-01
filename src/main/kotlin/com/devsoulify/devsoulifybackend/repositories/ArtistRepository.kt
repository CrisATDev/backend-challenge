package com.devsoulify.devsoulifybackend.repositories

import com.devsoulify.devsoulifybackend.domain.Artist
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ArtistRepository : JpaRepository<Artist, UUID> {
    fun findBySpotifyId(spotifyId: String): Artist?
}
