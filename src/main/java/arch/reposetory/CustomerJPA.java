package arch.reposetory;

import arch.entity.database.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJPA extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByLogin(String login);
}
