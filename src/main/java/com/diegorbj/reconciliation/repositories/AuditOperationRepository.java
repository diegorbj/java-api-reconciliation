package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.AuditOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditOperationRepository extends JpaRepository<AuditOperation, Long>, AuditOperationCustomRepository {

    List<AuditOperation> getWithFilter(OperationFilterParam params);

}
