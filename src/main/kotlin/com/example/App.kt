package com.example

import kotlinx.serialization.Serializable
import pl.treksoft.kvision.Application
import pl.treksoft.kvision.form.FormPanel
import pl.treksoft.kvision.form.formPanel
import pl.treksoft.kvision.form.text.Password
import pl.treksoft.kvision.form.text.Text
import pl.treksoft.kvision.html.Button
import pl.treksoft.kvision.html.ButtonStyle
import pl.treksoft.kvision.html.button
import pl.treksoft.kvision.html.div
import pl.treksoft.kvision.i18n.DefaultI18nManager
import pl.treksoft.kvision.i18n.I18n
import pl.treksoft.kvision.i18n.I18n.tr
import pl.treksoft.kvision.modal.Dialog
import pl.treksoft.kvision.panel.root
import pl.treksoft.kvision.require
import pl.treksoft.kvision.startApplication

@Serializable
data class Credentials(val username: String, val password: String)

class LoginWindow : Dialog<Credentials>() {
    private val loginPanel: FormPanel<Credentials>
    private val loginButton: Button

    init {
        loginPanel = formPanel {
            add(Credentials::username, Text(label = "Login:"), required = true)
            add(Credentials::password, Password(label = "Password:"))
        }
        loginButton = Button("Login", "fas fa-check", ButtonStyle.PRIMARY).onClick {
            if (loginPanel.validate()) {
                println(loginPanel.getData())
            }
        }
        addButton(loginButton)
    }
}

class App : Application() {
    init {
        require("css/kvapp.css")
    }

    override fun start() {
        I18n.manager =
            DefaultI18nManager(
                mapOf(
                    "pl" to require("i18n/messages-pl.json"),
                    "en" to require("i18n/messages-en.json")
                )
            )

        root("kvapp") {
            LoginWindow().show()
        }
    }
}

fun main() {
    startApplication(::App)
}
