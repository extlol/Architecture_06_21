package arch.controller.v1;

import arch.entity.RequestResponse;
import arch.entity.customer.Customer;
import arch.entity.factory.FactoryRequirements;
import arch.service.DatabaseService;
import arch.service.WorkspaceService;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("v1/requirement")
public class RequirementsController {
    private final Gson gson = new Gson();
    private final WorkspaceService workspace;
    private final DatabaseService database;

    @Autowired
    public RequirementsController(DatabaseService database, WorkspaceService workspaceService) {
        this.database = database;
        this.workspace = workspaceService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void getRequirement(@RequestHeader String sessionId) {

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createRequirement(@RequestHeader String sessionId,
                                                    @RequestBody FactoryRequirements requirements) {
        try {
            Customer customer = workspace.getCustomer(sessionId);
            database.createRequirements(requirements);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(gson.toJson(
                    new RequestResponse(false, "Error create order, " + e.getMessage())));
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> workInRequirement(@RequestHeader String sessionId, @RequestBody int id) {
        try {
            Customer customer = workspace.getCustomer(sessionId);
            database.workInRequirement(customer.getId(), id);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(gson.toJson(
                    new RequestResponse(false, "Error create order, " + e.getMessage())));
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> endRequirement(@RequestHeader String sessionId, @RequestBody int id) {
        try {
            Customer customer = workspace.getCustomer(sessionId);
            database.endRequirement(id);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(gson.toJson(
                    new RequestResponse(false, "Error create order, " + e.getMessage())));
        }
    }
}
