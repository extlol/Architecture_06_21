package arch.controller.v1;

import arch.Workspace;
import arch.entity.Customer;
import arch.entity.database.OrderEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Log4j2
@RestController
@RequestMapping("factory/v1")
public class FactoryController {
    private final Workspace workspace;

    @Autowired
    public FactoryController(Workspace workspace) {
        this.workspace = workspace;
    }

    @PostMapping("/auth")
    public ResponseEntity<Customer> auth(@RequestHeader("sessionId") String sessionId, String login, String password) {
        workspace.authUser(sessionId, login, password);
        return ResponseEntity.ok(workspace.getCustomer(sessionId));
    }

    @MessageMapping("/socket/get-orders")
    public Set<OrderEntity> getOrders(@Header("sessionId") String sessionId) {
    }
}
