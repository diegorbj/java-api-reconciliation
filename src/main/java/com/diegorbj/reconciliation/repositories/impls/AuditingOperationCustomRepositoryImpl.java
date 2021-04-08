package com.diegorbj.reconciliation.repositories.impls;

import com.diegorbj.reconciliation.domain.*;
import com.diegorbj.reconciliation.repositories.AuditingOperationCustomRepository;
import com.diegorbj.reconciliation.repositories.criterias.params.AuditingOperationFilterParam;
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
public class AuditingOperationCustomRepositoryImpl implements AuditingOperationCustomRepository {

    @PersistenceContext//(unitName="reconciliation")
    private EntityManager _entityManager;

    @Override
    public List<AuditingOperation> getWithFilter(AuditingOperationFilterParam params) {
        CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
        CriteriaQuery<AuditingOperation> query = criteriaBuilder.createQuery(AuditingOperation.class);
        Root<AuditingOperation> sourceTransaction = query.from(AuditingOperation.class);
        List<Predicate> predicates = new ArrayList<>();

        if (params.getFinancialInstitution() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("financialInstitution"), params.getFinancialInstitution()));
        }
        if (params.getMerchant() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("merchant"), params.getMerchant()));
        }
        if (params.getDateFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(sourceTransaction.get("date"), params.getDateFrom()));
        }
        if (params.getDateTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(sourceTransaction.get("date"), params.getDateTo()));
        }
        if (params.getAuthorizationId() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("authorizationId"), params.getAuthorizationId()));
        }
        if (params.getPointOfSaleId() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("pointOfSaleId"), params.getPointOfSaleId()));
        }
        if (params.getAuthorizationCode() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("authorizationCode"), params.getAuthorizationCode()));
        }
        if (params.getTransactionId() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("transactionId"), params.getTransactionId()));
        }
        if (params.getTransactionStatus() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("transactionStatus"), params.getTransactionStatus()));
        }
        if (params.getTransactionInformation() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("transactionInformation"), params.getTransactionInformation()));
        }
        if (params.getRebateInformation() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("rebateInformation"), params.getRebateInformation()));
        }
        if (params.getCardType() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("cardType"), params.getCardType()));
        }
        if (params.getModality() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("modality"), params.getModality()));
        }
        if (params.getServiceLabel() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("serviceLabel"), params.getServiceLabel()));
        }
        if (params.getFinancialService() != null) {
            predicates.add(criteriaBuilder.equal(sourceTransaction.get("financialService"), params.getFinancialService()));
        }
        if (params.getNumberOfInstallmentsFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(sourceTransaction.get("numberOfInstallments"), params.getNumberOfInstallmentsFrom()));
        }
        if (params.getNumberOfInstallmentsTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(sourceTransaction.get("numberOfInstallments"), params.getNumberOfInstallmentsTo()));
        }
        if (params.getGrossAmountFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(sourceTransaction.get("grossAmount"), params.getGrossAmountFrom()));
        }
        if (params.getGrossAmountTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(sourceTransaction.get("grossAmount"), params.getGrossAmountTo()));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<AuditingOperation> queryResult = this._entityManager.createQuery(query);

        return queryResult.getResultList();
    }

}
