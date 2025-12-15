package no.nav.eux.avslutt.rinasaker.naisjob

import no.nav.eux.avslutt.rinasaker.naisjob.integration.EuxAvsluttRinasakerClient
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application(
    val client: EuxAvsluttRinasakerClient
) : CommandLineRunner {

    override fun run(vararg args: String) {
        client.execute()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
