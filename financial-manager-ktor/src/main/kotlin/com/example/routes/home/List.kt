package com.example.routes.home

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.homeList() {
    get("/") {
        call.respond(
            FreeMarkerContent("home.ftl", mapOf("title" to "hello world"))
        )
    }
}