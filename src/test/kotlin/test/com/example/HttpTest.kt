package test.com.example

import com.example.runBlocking
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.features.logging.*
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.takeFrom
import kotlinx.coroutines.*
import kotlin.test.Test


class HttpTest {
/*
    @Test
    fun testResponse() = runBlocking {
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
        println(resp)
        throw Exception(resp.toString())
    }
*/
}