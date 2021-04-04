package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionFilterParam;

import java.util.List;

public interface SourceTransactionCustomRepository {

    List<SourceTransaction> getWithFilter(SourceTransactionFilterParam params);

}
