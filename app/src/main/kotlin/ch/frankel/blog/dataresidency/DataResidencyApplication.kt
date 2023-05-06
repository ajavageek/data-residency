package ch.frankel.blog.dataresidency

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@SpringBootApplication
class DataResidencyApplication

fun main(args: Array<String>) {
	runApplication<DataResidencyApplication>(*args) {
		addInitializers(beans())
	}
}

internal fun beans() = beans {
	bean {
		router {
			val handler = ThingyHandler(ref())
			GET("/thingies/{id}") { handler.findById(it) }
			GET("/thingies") { handler.findAll(it) }
			GET("/**") {
				val logger = LoggerFactory.getLogger(DataResidencyApplication::class.java)
				logger.error("Path not found: ${it.path()}")
				ServerResponse.notFound().build()
			}
		}
	}
}
