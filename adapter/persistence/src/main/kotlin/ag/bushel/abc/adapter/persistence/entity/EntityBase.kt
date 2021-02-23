package ag.bushel.abc.adapter.persistence.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * Spring annotations for CreatedDate, CreatedBy, LastModifiedDate, and LastModifiedBy utilize the AuditingEntityListener
 * to auto-populate those fields on persistence. JpaAuditingConfiguration class is used to configure these settings.
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class EntityBase(
    @Id open val id: UUID
) {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant = Instant.now()

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    var createdBy: String? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: Instant = Instant.now()

    @LastModifiedBy
    @Column(name = "updated_by")
    var updatedBy: String? = null
}
