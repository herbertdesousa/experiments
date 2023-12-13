package com.example.routes.home

import io.ktor.server.routing.*

fun Routing.homeRouting() {
    route("/") {
        homeList()
    }
}