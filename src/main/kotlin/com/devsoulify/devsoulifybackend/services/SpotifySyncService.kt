package com.devsoulify.devsoulifybackend.services

import com.devsoulify.devsoulifybackend.domain.*
import com.devsoulify.devsoulifybackend.repositories.*
import com.devsoulify.devsoulifybackend.spotify.client.SpotifyApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SpotifySyncService(
    private val spotifyApiClient: SpotifyApiClient,
    private val artistRepository: ArtistRepository,
    private val albumRepository: AlbumRepository,
    private val categoryRepository: CategoryRepository
) {

    suspend fun syncAll() = withContext(Dispatchers.IO) {

        println("SYNC -> Starting new releases sync...")

        // ---------------------------------------------
        // 1. NEW RELEASES (ALBUMS + ARTISTS)
        // ---------------------------------------------
        val newReleases = spotifyApiClient.getNewReleases()

        newReleases.albums.items.forEach { albumDto ->

            // Save artists
            val artists = albumDto.artists.map { artistDto ->
                artistRepository.findBySpotifyId(artistDto.id)
                    ?: artistRepository.save(
                        Artist(
                            spotifyId = artistDto.id,
                            name = artistDto.name
                        )
                    )
            }.toMutableSet()

            // Save album
            if (albumRepository.findBySpotifyId(albumDto.id) == null) {
                albumRepository.save(
                    Album(
                        spotifyId = albumDto.id,
                        name = albumDto.name,
                        releaseDate = parseReleaseDate(albumDto.release_date),
                        imageUrl = albumDto.images.firstOrNull()?.url,
                        artists = artists
                    )
                )
            }
        }

        println("SYNC -> New releases OK")

        // ---------------------------------------------
        // 2. CATEGORIES
        // ---------------------------------------------
        val categories = spotifyApiClient.getCategories()

        categories.categories.items.forEach { catDto ->
            if (categoryRepository.findBySpotifyId(catDto.id) == null) {
                categoryRepository.save(
                    Category(
                        spotifyId = catDto.id,
                        name = catDto.name,
                        iconUrl = catDto.icons.firstOrNull()?.url
                    )
                )
            }
        }

        println("SYNC -> Categories OK")
        println("SYNC -> FINISHED SUCCESSFULLY")
    }

    // Spotify returns dates like "2024", "2024-01", "2024-01-12"
    private fun parseReleaseDate(value: String): LocalDate {
        return when {
            value.length == 4 -> LocalDate.of(value.toInt(), 1, 1)
            value.length == 7 -> LocalDate.parse("$value-01")
            else -> LocalDate.parse(value)
        }
    }
}
