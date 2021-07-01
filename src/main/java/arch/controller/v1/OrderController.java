package arch.controller.v1;

import arch.service.WorkspaceService;
import arch.entity.RequestResponse;
import arch.entity.customer.Customer;
import arch.entity.factory.FactoryOrder;
import arch.service.DatabaseService;

import com.google.gson.Gson;

import javax.validation.Valid;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrder(@RequestHeader String sessionId,
                                              @RequestBody @Valid FactoryOrder order) {
        try {
            Customer customer = workspace.getCustomer(sessionId);
            database.createOrder(customer, order);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(gson.toJson(
                    new RequestResponse(false, "Error create order, " + e.getMessage())));
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cancelOrder(@RequestHeader String sessionId,
                                              @RequestBody @Valid FactoryOrder order) {
        try {
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(gson.toJson(
                    new RequestResponse(false, "Error cancel order, " + e.getMessage())));
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendOrder(@RequestHeader String sessionId,
                                            @RequestBody int id) {
        try {
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(gson.toJson(
                    new RequestResponse(false, "Error send order, " + e.getMessage())));
        }
    }
}
