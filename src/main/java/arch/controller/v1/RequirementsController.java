package arch.controller.v1;

import arch.entity.RequestResponse;
import arch.entity.RestException;
import arch.entity.customer.Customer;
import arch.entity.customer.CustomerRequirements;
import arch.entity.factory.FactoryRequirements;
import arch.service.DatabaseService;
import arch.service.WorkspaceService;

import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin
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

    @Operation(summary = "Get user requirement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response user requirement",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CustomerRequirements.class)))}),
            @ApiResponse(responseCode = "400", description = "Failed create requirement")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void getRequirement(@RequestHeader String sessionId) {
    }

    @Operation(summary = "Create requirement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful create requirement",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Failed create requirement")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createRequirement(@RequestHeader String sessionId,
                                                    @RequestBody FactoryRequirements requirements) {
        try {
            Customer customer = workspace.getCustomer(sessionId);
            database.createRequirements(requirements);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }

    @Operation(summary = "Took the job requirement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful took the job requirement",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Failed took the job requirement")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> workInRequirement(@RequestHeader String sessionId, @RequestBody int id) {
        try {
            Customer customer = workspace.getCustomer(sessionId);
            database.workInRequirement(customer.getId(), id);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }

    @Operation(summary = "End requirement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully end requirement",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Failed end requirement")
    })
    @DeleteMapping(value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> endRequirement(@RequestHeader String sessionId, @PathVariable int id) {
        try {
            Customer customer = workspace.getCustomer(sessionId);
            database.endRequirement(id);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }
}
