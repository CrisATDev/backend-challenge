package com.devsoulify.devsoulifybackend.repositories

import com.devsoulify.devsoulifybackend.domain.Playlist
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PlaylistRepository : JpaRepository<Playlist, UUID> {
    fun findBySpotifyId(spotifyId: String): Playlist?
}
