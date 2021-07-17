package arch.controller.v1;

import arch.entity.RestException;
import arch.service.WorkspaceService;
import arch.entity.RequestResponse;
import arch.entity.customer.Authentication;
import arch.entity.customer.Customer;
import arch.entity.customer.WorkList;
import arch.service.DatabaseService;

import com.google.gson.Gson;

import java.util.Set;

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
@RequestMapping("v1/customer")
public class CustomerController {
    private final Gson gson = new Gson();
    private final WorkspaceService workspace;
    private final DatabaseService database;

    @Autowired
    public CustomerController(WorkspaceService workspaceService, DatabaseService database) {
        this.workspace = workspaceService;
        this.database = database;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/workspace")
    public String workspace() {
        return "workspace";
    }

    @Operation(summary = "Login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully login",
            content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect data")
    })
    @PostMapping("/auth")
    public ResponseEntity<Customer> auth(@RequestHeader("sessionId") String sessionId,
                                         @RequestBody Authentication authentication) {
        try {
            workspace.authUser(sessionId, authentication);
            return ResponseEntity.ok(workspace.getCustomer(sessionId));
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }

    @Operation(summary = "getWorkList")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response work list from user",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = WorkList.class)))}),
            @ApiResponse(responseCode = "400", description = "Error find user worklist")
    })
    @GetMapping("/worklist")
    public ResponseEntity<String> getWorkList(@RequestHeader("sessionId") String sessionId) {
        try {
            Customer customer = workspace.getCustomer(sessionId);
            Set<WorkList> workList = database.getWorkList(customer.getId(), customer.getRole());
            return ResponseEntity.ok(gson.toJson(workList));
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }

    @Operation(summary = "Logout user by session id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logout"),
            @ApiResponse(responseCode = "400", description = "User not found ")
    })
    @DeleteMapping("/auth")
    public ResponseEntity<String> logout(@RequestHeader("sessionId") String sessionId) {
        try {
            workspace.removeUser(sessionId);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }
}
