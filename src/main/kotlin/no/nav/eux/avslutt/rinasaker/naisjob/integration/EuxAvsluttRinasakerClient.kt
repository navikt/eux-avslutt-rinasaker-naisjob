package no.nav.eux.avslutt.rinasaker.naisjob.integration

import io.github.oshai.kotlinlogging.KotlinLogging.logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus.GATEWAY_TIMEOUT
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestClient

@Component
class EuxAvsluttRinasakerClient(
    @Value("\${prosess}")
    val prosess: String,
    @Value("\${endpoint.eux-avslutt-rinasaker}")
    val euxAvsluttRinasakerUrl: String,
) {

    val log = logger {}

    fun execute() {
        log.info { "execute: $prosess" }
        val uri = "${euxAvsluttRinasakerUrl}/api/v1/prosesser/$prosess/execute"
        try {
            RestClient
                .create()
                .post()
                .uri(uri)
                .contentType(APPLICATION_JSON)
                .accept(MediaType.ALL)
                .retrieve()
                .toBodilessEntity()
        } catch (e: HttpServerErrorException) {
            when (e.statusCode) {
                GATEWAY_TIMEOUT -> log.info { "Timeout mot server, venter ikke på svar" }
                else -> throw e
            }
        }
    }
}
