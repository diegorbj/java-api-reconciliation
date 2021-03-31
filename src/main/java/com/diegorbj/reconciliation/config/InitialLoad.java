package com.diegorbj.reconciliation.config;

import com.diegorbj.reconciliation.domain.*;
import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import com.diegorbj.reconciliation.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.List;

@Configuration
@Profile("test")
public class InitialLoad implements CommandLineRunner {

    @Autowired
    private CardTypeService _cardTypeService;

    @Autowired
    private FinancialInstitutionService _financialInstitutionService;

    @Autowired
    private FinancialServiceService _financialServiceService;

    @Autowired
    private MerchantService _merchantService;

    @Autowired
    private ModalityService _modalityService;

    @Autowired
    private ServiceLabelService _serviceLabelService;

    @Autowired
    private SourceTransactionService _sourceTransactionService;

    @Autowired
    private InstallmentService _installmentService;

    @Override
    public void run(String... args) throws Exception {

        CardType ct1 = new CardType(null, "Gold");
        CardType ct2 = new CardType(null, "Platinum");
        CardType ct3 = new CardType(null, "Black");

        _cardTypeService.insert(ct1);
        _cardTypeService.insert(ct2);
        _cardTypeService.insert(ct3);

        FinancialInstitution fi1 = new FinancialInstitution(null, "Mint Co.");
        FinancialInstitution fi2 = new FinancialInstitution(null, "PayDay Inc.");

        _financialInstitutionService.insert(fi1);
        _financialInstitutionService.insert(fi2);

        FinancialService fs1 = new FinancialService(null, "Credit Card");
        FinancialService fs2 = new FinancialService(null, "Debit Card");

        _financialServiceService.insert(fs1);
        _financialServiceService.insert(fs2);

        Merchant m1 = new Merchant(null, "Magazine 25th Av.");
        Merchant m2 = new Merchant(null, "Mini 5th Av.");
        Merchant m3 = new Merchant(null, "Super DC");

        _merchantService.insert(m1);
        _merchantService.insert(m2);
        _merchantService.insert(m3);

        Modality mod1 = new Modality(null, "Magnetic");
        Modality mod2 = new Modality(null, "Chip");
        Modality mod3 = new Modality(null, "Contactless");

        _modalityService.insert(mod1);
        _modalityService.insert(mod2);
        _modalityService.insert(mod3);

        ServiceLabel sl1 = new ServiceLabel(null, "Visa");
        ServiceLabel sl2 = new ServiceLabel(null, "Master");
        ServiceLabel sl3 = new ServiceLabel(null, "Amex");

        _serviceLabelService.insert(sl1);
        _serviceLabelService.insert(sl2);
        _serviceLabelService.insert(sl3);

        SourceTransaction st1 = new SourceTransaction(null, Instant.now(), 123456L, "", "987D54", TransactionStatus.APPROVED, 1, 100.00, "123456******3456", m2, fi1, fs2, sl2, ct2, mod2);
        st1 = _sourceTransactionService.insert(st1);
        _installmentService.insert(new Installment(st1, 1, 100.00));

        SourceTransaction st2 = new SourceTransaction(null, Instant.now(), 789456L, "", "4687F3", TransactionStatus.APPROVED, 2, 150.00, "123456******3456", m1, fi2, fs1, sl1, ct3, mod3);
        st2 = _sourceTransactionService.insert(st2);
        _installmentService.insert(new Installment(st2, 1, 75.00));
        _installmentService.insert(new Installment(st2, 2, 75.00));

        SourceTransaction st3 = new SourceTransaction(null, Instant.now(), 456132L, "", "852465", TransactionStatus.APPROVED, 3, 100.00, "123456******3456", m3, fi2, fs1, sl1, ct1, mod3);
        st3 = _sourceTransactionService.insert(st3);
        _installmentService.insert(new Installment(st3, 1, 33.34));
        _installmentService.insert(new Installment(st3, 2, 33.33));
        _installmentService.insert(new Installment(st3, 3, 33.33));

        List<Installment> list = _installmentService.findAll();

        for (Installment i : list){
            System.out.println(i);
        }

    }

}
