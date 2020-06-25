package com.example

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.takeFrom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.promise
import kotlinx.serialization.Serializable
import pl.treksoft.kvision.Application
import pl.treksoft.kvision.form.FormPanel
import pl.treksoft.kvision.form.formPanel
import pl.treksoft.kvision.form.text.Password
import pl.treksoft.kvision.form.text.Text
import pl.treksoft.kvision.html.*
import pl.treksoft.kvision.i18n.DefaultI18nManager
import pl.treksoft.kvision.i18n.I18n
import pl.treksoft.kvision.i18n.I18n.tr
import pl.treksoft.kvision.modal.Dialog
import pl.treksoft.kvision.panel.root
import pl.treksoft.kvision.require
import pl.treksoft.kvision.startApplication

fun runBlocking(block: suspend (scope : CoroutineScope) -> Unit): dynamic = GlobalScope.promise { block(this) }

class App : Application() {
    override fun start() {
        root("kvapp") {
            runBlocking {
                val client = HttpClient(Js) {
                    followRedirects = false
                    install(Logging) {
                        logger = Logger.DEFAULT
                        level = LogLevel.ALL
                    }
                }
                val resp: HttpResponse = client.post {
                    url.takeFrom("http://localhost:3000/api/v1/auth/logout")
                }
                span(resp.toString())
            } as Unit
        }
    }
}

fun main() {
    startApplication(::App)
}
