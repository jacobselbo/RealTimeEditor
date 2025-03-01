package com.jacobselbo.plugins

import com.jacobselbo.services.PageService
import io.ktor.server.application.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

/**
 * Installs Koin
 *  - Instantiates PageService
 */
fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()

        modules(module {
            singleOf(::PageService)
        })
    }
}
