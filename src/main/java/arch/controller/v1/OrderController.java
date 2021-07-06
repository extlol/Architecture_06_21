package arch.controller.v1;

import arch.entity.RestException;
import arch.service.WorkspaceService;
import arch.entity.RequestResponse;
import arch.entity.customer.Customer;
import arch.entity.factory.FactoryOrder;
import arch.service.DatabaseService;

import com.google.gson.Gson;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/order")
public class OrderController {
    private final Gson gson = new Gson();
    private final DatabaseService database;
    private final WorkspaceService workspace;

    @Autowired
    public OrderController(DatabaseService database, WorkspaceService workspaceService) {
        this.database = database;
        this.workspace = workspaceService;
    }

    @Operation(summary = "createOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully create Order",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect data for create order")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrder(@RequestHeader String sessionId,
                                              @RequestBody @Valid FactoryOrder order) {
        try {
            Customer customer = workspace.getCustomer(sessionId);
            database.createOrder(customer, order);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }

    @Operation(summary = "Cancel order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully create Order",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Failed cancel order")
    })
    @PutMapping(value = "/cancel",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> cancelOrder(@RequestHeader String sessionId,
                                              @RequestBody @Valid FactoryOrder order) {
        try {
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }

    @Operation(summary = "Send order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully send Order",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Failed send order")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendOrder(@RequestHeader String sessionId,
                                            @RequestBody int id) {
        try {
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            throw new RestException(e.getMessage());
        }
    }
}
