package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.AuditingOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.AuditingOperationFilterParam;

import java.util.List;

public interface AuditingOperationCustomRepository {

    List<AuditingOperation> getWithFilter(AuditingOperationFilterParam params);

}
