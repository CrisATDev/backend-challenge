package com.devsoulify.devsoulifybackend.repositories

import com.devsoulify.devsoulifybackend.domain.Album
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AlbumRepository : JpaRepository<Album, UUID> {
    fun findBySpotifyId(spotifyId: String): Album?
}
