import org.springframework.data.annotation.Id
import java.util.*

data class PullRequest(
  @Id
  val id: UUID? = UUID.randomUUID(),
  val state: String,
  val title: String,
  val updateDate: Date,
  val closeDate: Date?,
  val createDate: Date
)