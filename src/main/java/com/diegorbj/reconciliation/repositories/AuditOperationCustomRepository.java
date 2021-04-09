package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.AuditOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;

import java.util.List;

public interface AuditOperationCustomRepository {

    List<AuditOperation> getWithFilter(OperationFilterParam params);

}
