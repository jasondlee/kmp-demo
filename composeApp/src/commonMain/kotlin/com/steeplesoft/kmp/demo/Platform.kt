package com.steeplesoft.kmp.demo

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

fun globalClientConf(config: HttpClientConfig<*>) {
    with(config) {
        defaultRequest {
            url("http://192.168.1.204:8081/")
            headers {
                appendMissing(HttpHeaders.Accept, listOf("application/json"))
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }) // Example: Register JSON content transformation
            // Add more transformations as needed for other content types
        }

        /*
        install(Auth) {
            // Configure authentication
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiQURNSU4iXX0sImlzcyI6Imh0dHBzOi8vc3RlZXBsZXNvZnQuY29tL2lzc3VlciIsImdpdmVuX25hbWUiOiJBbmdlbGEiLCJmYW1pbHlfbmFtZSI6IkxlZSIsInVwbiI6ImFuZ2VsYUBzdGVlcGxlc29mdC5jb20iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhbmdlbGFAc3RlZXBsZXNvZnQuY29tIiwiaWF0IjoxNzMwMDc4NTA1LCJleHAiOjE3MzAwODAzMDUsImNodXJjaF9pZCI6MSwic3ViIjoiYW5nZWxhQHN0ZWVwbGVzb2Z0LmNvbSIsImdyb3VwcyI6WyJBRE1JTiJdLCJqdGkiOiI0YWZlNTBkZC1jM2ZkLTQ4MWYtODM4OC05N2NiNmMyNjU1YTEifQ.l-hWJ4Zz3gdI9W4mByaKxi31_H3uzJVtBomCDcVILMNphfGBcM3kx8qSGx_SJWCwuX6WqeO4YOAqrz9HeHB5WNxUHU9QUh96GIhd0iyR73_MZbZaQmbrvZ5EeLxEoVVDBsUTDPrOEslfhU_MJ1ZoW4jJYT0Yqlz_N9m1N01FeedJipNsgBnFdTDbInel2CR6a8ToHxgP4_lHXOg9_TOshxy0IeBw8WaKETbR3jykboUFPHna8q_cUcngykpDIleYOcGVW0Txa7UsVWYWnl9mpg5nu_OKNoeT1NdEpVHXkj7e9ms6Y4yJnoW1lhx4SWT5oXPEWqFir59ur-hJ8X8i6A",
                        refreshToken = null
                    )
                }
            }
        }
        */
    }
}

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient
