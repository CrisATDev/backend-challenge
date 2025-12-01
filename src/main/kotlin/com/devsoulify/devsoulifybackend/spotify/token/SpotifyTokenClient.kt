package com.devsoulify.devsoulifybackend.spotify.token

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@Component
class SpotifyTokenClient(
    @Value("\${SPOTIFY_CLIENT_ID}") private val clientId: String,
    @Value("\${SPOTIFY_CLIENT_SECRET}") private val clientSecret: String
) {

    private val webClient = WebClient.builder()
        .baseUrl("https://accounts.spotify.com/api")
        .defaultHeaders {
            val credentials = "$clientId:$clientSecret"
            val encoded = Base64.getEncoder().encodeToString(credentials.toByteArray())
            it.add("Authorization", "Basic $encoded")
        }
        .build()

    suspend fun getToken(): SpotifyTokenResponse {
        return webClient.post()
            .uri("/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(
                BodyInserters.fromFormData("grant_type", "client_credentials")
            )
            .retrieve()
            .bodyToMono(SpotifyTokenResponse::class.java)
            .awaitSingle()
    }
}
