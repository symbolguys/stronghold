import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono;
import java.util.*

@RestController
@RequestMapping("/test")
class PullRequestController {

    @GetMapping("/pullRequests")
    fun testGet(): Flux<List<PullRequest>> {
        var prList=mutableListOf<PullRequest>()
        prList.add(PullRequest(UUID.randomUUID(),"MERGED","Great Title", Date(101199), Date(15122018), Date(12101990) ))
        return Flux.just(prList)
    }

}
