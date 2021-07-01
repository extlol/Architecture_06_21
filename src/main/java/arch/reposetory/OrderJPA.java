package arch.reposetory;

import arch.entity.database.OrderEntity;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJPA extends JpaRepository<OrderEntity, Integer> {
    Set<OrderEntity> findAllByCustomerId(int customerId);
    Set<OrderEntity> findAllByStatusNotIn(String[] status);
}
