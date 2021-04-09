package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.ConfirmOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;

import java.util.List;

public interface ConfirmOperationCustomRepository {

    List<ConfirmOperation> getWithFilter(OperationFilterParam params);

}
