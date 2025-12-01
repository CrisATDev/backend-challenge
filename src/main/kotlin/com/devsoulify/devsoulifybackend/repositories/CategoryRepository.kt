package com.devsoulify.devsoulifybackend.repositories

import com.devsoulify.devsoulifybackend.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CategoryRepository : JpaRepository<Category, UUID> {
    fun findBySpotifyId(spotifyId: String): Category?
}
