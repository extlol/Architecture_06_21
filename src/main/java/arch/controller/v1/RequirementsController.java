package arch.controller.v1;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("factory/v1/requirement")
public class RequirementsController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void getRequirement(@RequestHeader String sessionId) {

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createRequirement(@RequestHeader String sessionId) {

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void workInRequirement(@RequestHeader String sessionId) {

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void endRequirement(@RequestHeader String sessionId) {

    }
}
