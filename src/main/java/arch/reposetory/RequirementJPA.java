package arch.reposetory;

import arch.entity.database.RequirementsEntity;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RequirementJPA extends JpaRepository<RequirementsEntity, Integer> {
    Set<RequirementsEntity> findByWorkingCustomerOrWorkingCustomerIsNull(int id);
    @Modifying
    @Query(value = "UPDATE RequirementsEntity r SET r.workingCustomer = ?1, R.status = ?2  WHERE r.id = ?3")
    void workInRequirements(int customerId, String status, int id);
    @Modifying
    @Query(value = "UPDATE RequirementsEntity r SET r.status = ?1 WHERE r.id = ?2")
    void endRequirements(String status, int id);
}