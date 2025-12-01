package com.devsoulify.devsoulifybackend.spotify.token

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.springframework.stereotype.Service

@Service
class SpotifyTokenService(
    private val tokenClient: SpotifyTokenClient
) {

    private var cachedToken: String? = null
    private var expirationTime: Long = 0

    private val mutex = Mutex()

    suspend fun getValidToken(): String {
        return mutex.withLock {
            val now = System.currentTimeMillis()

            if (cachedToken == null || now >= expirationTime) {
                val response = tokenClient.getToken()

                cachedToken = response.access_token
                expirationTime = now + (response.expires_in * 1000)
            }

            cachedToken!!
        }
    }
}
