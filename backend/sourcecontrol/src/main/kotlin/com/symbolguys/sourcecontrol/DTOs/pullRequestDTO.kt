
@Entity(name = "pullrequests")
class pullRequestDTO{
    @Id
    @GeneratedValue
    val uuId: Long = 0
    val state: String
    val title: String
    val updateDate: Date
    val closeDate: Date
    val createDate: Date
}