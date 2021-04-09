package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.ConfirmOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfirmOperationRepository extends JpaRepository<ConfirmOperation, Long>, ConfirmOperationCustomRepository {

    List<ConfirmOperation> getWithFilter(OperationFilterParam params);

}
