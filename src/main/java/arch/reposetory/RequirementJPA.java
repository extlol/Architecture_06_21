package arch.reposetory;

import arch.entity.database.RequirementsEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RequirementJPA extends JpaRepository<RequirementsEntity, Integer> {
    Set<RequirementsEntity> findByWorkingCustomerOrWorkingCustomerIsNull(int id);
}
