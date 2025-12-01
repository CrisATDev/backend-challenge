package com.devsoulify.devsoulifybackend.spotify.client

import com.devsoulify.devsoulifybackend.spotify.dto.SpotifyCategoriesResponse
import com.devsoulify.devsoulifybackend.spotify.dto.SpotifyFeaturedPlaylistsResponse
import com.devsoulify.devsoulifybackend.spotify.dto.SpotifyNewReleasesResponse
import com.devsoulify.devsoulifybackend.spotify.token.SpotifyTokenService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class SpotifyApiClient(
    private val tokenService: SpotifyTokenService
) {

    private val webClient = WebClient.builder()
        .baseUrl("https://api.spotify.com/v1")
        .build()

    suspend fun getNewReleases(): SpotifyNewReleasesResponse {
        val token = tokenService.getValidToken()

        return webClient.get()
            .uri("/browse/new-releases")
            .header("Authorization", "Bearer $token")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(SpotifyNewReleasesResponse::class.java)
            .awaitSingle()
    }

    suspend fun getFeaturedPlaylists(): SpotifyFeaturedPlaylistsResponse {
        val token = tokenService.getValidToken()

        return webClient.get()
            .uri("/browse/featured-playlists")
            .header("Authorization", "Bearer $token")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(SpotifyFeaturedPlaylistsResponse::class.java)
            .awaitSingle()
    }

    suspend fun getCategories(): SpotifyCategoriesResponse {
        val token = tokenService.getValidToken()

        return webClient.get()
            .uri("/browse/categories")
            .header("Authorization", "Bearer $token")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(SpotifyCategoriesResponse::class.java)
            .awaitSingle()
    }
}
