package com.devsoulify.devsoulifybackend.controllers

import com.devsoulify.devsoulifybackend.services.SpotifySyncService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/debug")
class DebugController(
    private val spotifySyncService: SpotifySyncService
) {

    @GetMapping("/sync-test")
    suspend fun testSync(): String {
        println("DEBUG -> Start syncAll()")
        spotifySyncService.syncAll()
        return "OK"
    }
}
