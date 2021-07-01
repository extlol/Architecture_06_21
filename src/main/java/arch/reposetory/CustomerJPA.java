package arch.reposetory;

import arch.entity.database.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJPA extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByLogin(String login);
}
