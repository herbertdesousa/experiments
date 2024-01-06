package com.example.plugins

import com.example.routes.financesRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        financesRouting()
    }
}
