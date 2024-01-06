package com.example.routes

import com.example.dao.FinanceSave
import com.example.dao.dao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.financesRouting() {
    route("/finances") {
        get {
            call.respond(dao.financesFindAll())
        }

        post {
            val finance = call.receive<FinanceSave>()

            val id = dao.financesCreate(finance)

            call.respond(mapOf("user_id" to id))
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val finance = call.receive<FinanceSave>()

            dao.financesUpdate(id, finance)

            call.respond(HttpStatusCode.OK)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")

            dao.financesDelete(id)

            call.respond(HttpStatusCode.OK)
        }
    }
}
