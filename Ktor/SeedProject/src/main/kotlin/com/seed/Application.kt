package com.seed

import com.example.plugins.configureSockets
import com.seed.plugins.*
import com.seed.model.app.Sample
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

    var sample = Sample()

    sample.addSampleUser()
    sample.addSampleStore()
    sample.addSampleReservation()
    sample.addSampleTable()
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
    configureSockets()
}
