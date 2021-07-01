package arch.controller.v1;

import arch.Workspace;
import arch.entity.customer.Authentication;
import arch.entity.customer.Customer;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("factory/v1/customer")
public class CustomerController {
    private final Workspace workspace;

    @Autowired
    public CustomerController(Workspace workspace) {
        this.workspace = workspace;
    }

    @PostMapping("/auth")
    public ResponseEntity<Customer> auth(@RequestHeader("sessionId") String sessionId,
                                         @RequestBody Authentication authentication) {
        workspace.authUser(sessionId, authentication);
        return ResponseEntity.ok(workspace.getCustomer(sessionId));
    }
}
