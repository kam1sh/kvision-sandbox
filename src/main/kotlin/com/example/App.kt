package com.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import pl.treksoft.kvision.Application
import pl.treksoft.kvision.html.span
import pl.treksoft.kvision.panel.root
import pl.treksoft.kvision.startApplication

fun runBlocking(block: suspend (scope : CoroutineScope) -> Unit): dynamic = GlobalScope.promise { block(this) }

class App : Application() {
    override fun start() {
        root("kvapp") {
            span("CLICK ME") {
                id = "login-btn"
            }
        }
        eval("""
        $(document).ready(function() {
            $('.ui.modal').modal()
            $('.ui.modal').modal("attach events", "#login-btn")
        })
        """.trimIndent())

/*
        jQuery(document).ready {
            it!!(".ui.modal").asDynamic().modal()
            it!!(".ui.modal").asDynamic().modal("attach events", "#login-btn")
        }
*/
    }
}

fun main() {
    startApplication(::App)
}
