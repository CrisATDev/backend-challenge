package com.devsoulify.devsoulifybackend.controllers

import com.devsoulify.devsoulifybackend.services.SpotifySyncService
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminSyncController(
    private val spotifySyncService: SpotifySyncService
) {

    @PostMapping("/sync")
    suspend fun sync(): String {
        println(">>> SYNC ENDPOINT CALLED <<<")
        spotifySyncService.syncAll()
        return "OK"
    }

}
