package ch.frankel.blog.dataresidency

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

@Entity
internal data class Owner(
    @Id val id: String,
    val country: String,
    val name: String,
)

@Entity
internal data class Thingy(
    @Id val id: Long,
    val name: String,
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    val owner: Owner,
)

internal interface ThingyRepository : JpaRepository<Thingy, Long>

internal class ThingyHandler(private val repo: ThingyRepository) {

    fun findById(req: ServerRequest) =
        req.pathVariable("id").toLongOrNull()?.let { id ->
            repo.findByIdOrNull(id)
                ?.let { ServerResponse.ok().body(it) }
                ?: ServerResponse.notFound().build()
        } ?: ServerResponse.badRequest().build()

    fun findAll(req: ServerRequest): ServerResponse =
        ServerResponse.ok().body(repo.findAll())
}
