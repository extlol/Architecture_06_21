package arch.controller.v1;

import arch.Workspace;
import arch.entity.RequestResponse;
import arch.entity.customer.Authentication;
import arch.entity.customer.Customer;
import arch.entity.customer.WorkList;
import arch.service.DatabaseService;

import com.google.gson.Gson;

import java.util.Set;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("v1/customer")
public class CustomerController {
    private final Gson gson = new Gson();
    private final Workspace workspace;
    private final DatabaseService database;


    @Autowired
    public CustomerController(Workspace workspace, DatabaseService database) {
        this.workspace = workspace;
        this.database = database;
    }

    @PostMapping("/auth")
    public ResponseEntity<Customer> auth(@RequestHeader("sessionId") String sessionId,
                                         @RequestBody Authentication authentication) {
        workspace.authUser(sessionId, authentication);
        return ResponseEntity.ok(workspace.getCustomer(sessionId));
    }

    @GetMapping("/worklist")
    public ResponseEntity<String> getWorkList(@RequestHeader("sessionId") String sessionId) {
        Customer customer = workspace.getCustomer(sessionId);
        Set<WorkList> workList = database.getWorkList(customer.getId(), customer.getRole());
        return ResponseEntity.ok(gson.toJson(workList));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("sessionId") String sessionId) {
        try {
            workspace.removeUser(sessionId);
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true)));
        } catch (Exception e) {
            return ResponseEntity.ok(gson.toJson(new RequestResponse(true, "Logout error, " + e.getMessage())));
        }
    }
}
