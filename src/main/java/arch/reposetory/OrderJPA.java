package arch.reposetory;

import arch.entity.database.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderJPA extends JpaRepository<OrderEntity, Integer> {
    Set<OrderEntity> findAllByCuAndCustomerId(int customerId);
}
