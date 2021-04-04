package com.diegorbj.reconciliation.repositories.impls;

import com.diegorbj.reconciliation.domain.*;
import com.diegorbj.reconciliation.repositories.SourceTransactionCustomRepository;
import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionFilterParam;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SourceTransactionCustomRepositoryImpl implements SourceTransactionCustomRepository {

    @PersistenceContext//(unitName="reconciliation")
    private EntityManager _entityManager;

    @Override
    public List<SourceTransaction> getWithFilter(SourceTransactionFilterParam params) {
        CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
        CriteriaQuery<SourceTransaction> query = criteriaBuilder.createQuery(SourceTransaction.class);
        Root<SourceTransaction> sourceTransaction = query.from(SourceTransaction.class);
        List<Predicate> predicates = new ArrayList<>();

        if (params.getUniqueSequentialNumber() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("uniqueSequentialNumber"), params.getUniqueSequentialNumber()));
        }
        if (params.getNumberOfInstallmentsFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(sourceTransaction.get("numberOfInstallments"), params.getNumberOfInstallmentsFrom()));
        }
        if (params.getNumberOfInstallmentsTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(sourceTransaction.get("numberOfInstallments"), params.getNumberOfInstallmentsTo()));
        }
        if (params.getGrossAmountFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(sourceTransaction.get("grossAmount"), params.getNumberOfInstallmentsFrom()));
        }
        if (params.getGrossAmountTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(sourceTransaction.get("grossAmount"), params.getNumberOfInstallmentsTo()));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.stream().toArray(Predicate[]::new));
        }

        TypedQuery<SourceTransaction> queryResult = this._entityManager.createQuery(query);

        return queryResult.getResultList();
    }

}
