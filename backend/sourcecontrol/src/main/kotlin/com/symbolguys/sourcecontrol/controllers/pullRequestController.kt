import com.symbolguys.sourcecontrol.jpa.PullRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono;
import java.util.*

@RestController
@RequestMapping("/pullRequests")
class PullRequestController (val prRepo: PullRequestRepository) {

    @GetMapping("/test")
    fun getMockedPullRequest() : Flux<List<PullRequest>>{
        var prList=mutableListOf<PullRequest>()
        prList.add(PullRequest(UUID.randomUUID(),"MERGED","Great Title", Date(101199), Date(15122018), Date(12101990) ))
        return Flux.just(prList)
    }
    @GetMapping
    fun getAllPullRequests(): Flux<PullRequest> {
        return prRepo.findAll();
    }

    @PostMapping
    fun createPullRequest(@RequestBody pullRequest: PullRequest): Mono<PullRequest> {
        return prRepo.save(pullRequest)
    }

    @DeleteMapping("/{id}")
    fun deletePullRequest(@PathVariable id: UUID): Mono<Void> {
        return prRepo.deleteById(id);
    }

    @PutMapping
    fun updatePullRequest(@RequestBody pullRequest: PullRequest): Mono<PullRequest>{
        return prRepo.save(pullRequest);
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): Mono<PullRequest>{
        return prRepo.findById(id);
    }
}
