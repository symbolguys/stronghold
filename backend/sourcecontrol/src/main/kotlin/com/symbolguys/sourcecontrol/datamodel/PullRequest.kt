import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
data class PullRequest(
  @Id @GeneratedValue
  val id: UUID? = UUID.randomUUID(),
  val state: String,
  val title: String,
  val updateDate: Date,
  val closeDate: Date?,
  val createDate: Date
)