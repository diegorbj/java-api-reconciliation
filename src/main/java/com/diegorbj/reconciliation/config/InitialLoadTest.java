package com.diegorbj.reconciliation.config;

import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import com.diegorbj.reconciliation.services.*;
import com.diegorbj.reconciliation.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;

@Configuration
@Profile("test")
public class InitialLoadTest implements CommandLineRunner {

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

        CardTypeDTO ct1 = new CardTypeDTO(null, "Gold");
        CardTypeDTO ct2 = new CardTypeDTO(null, "Platinum");
        CardTypeDTO ct3 = new CardTypeDTO(null, "Black");

        ct1 = _cardTypeService.insert(ct1);
        ct2 = _cardTypeService.insert(ct2);
        ct3 = _cardTypeService.insert(ct3);

        FinancialInstitutionDTO fi1 = new FinancialInstitutionDTO(null, "G-Force Co.", FinancialInstitutionCode.GFORCE);
        FinancialInstitutionDTO fi2 = new FinancialInstitutionDTO(null, "Code One Inc.", FinancialInstitutionCode.CODEONE);

        fi1 = _financialInstitutionService.insert(fi1);
        fi2 = _financialInstitutionService.insert(fi2);

        FinancialServiceDTO fs1 = new FinancialServiceDTO(null, "Credit Card");
        FinancialServiceDTO fs2 = new FinancialServiceDTO(null, "Debit Card");

        fs1 = _financialServiceService.insert(fs1);
        fs2 = _financialServiceService.insert(fs2);

        MerchantDTO m1 = new MerchantDTO(null, "Magazine 25th Av.");
        MerchantDTO m2 = new MerchantDTO(null, "Mini 5th Av.");
        MerchantDTO m3 = new MerchantDTO(null, "Super DC");

        m1 = _merchantService.insert(m1);
        m2 = _merchantService.insert(m2);
        m3 = _merchantService.insert(m3);

        ModalityDTO mod1 = new ModalityDTO(null, "Magnetic");
        ModalityDTO mod2 = new ModalityDTO(null, "Chip");
        ModalityDTO mod3 = new ModalityDTO(null, "Contactless");

        mod1 = _modalityService.insert(mod1);
        mod2 = _modalityService.insert(mod2);
        mod3 = _modalityService.insert(mod3);

        ServiceLabelDTO sl1 = new ServiceLabelDTO(null, "Visa");
        ServiceLabelDTO sl2 = new ServiceLabelDTO(null, "Mastercard");
        ServiceLabelDTO sl3 = new ServiceLabelDTO(null, "American Express");

        sl1 = _serviceLabelService.insert(sl1);
        sl2 = _serviceLabelService.insert(sl2);
        sl3 = _serviceLabelService.insert(sl3);

        SourceTransactionDTO st1 = new SourceTransactionDTO(null, Instant.now(), 123456L, "", "987D54", TransactionStatus.APPROVED, 1, 130.00, "123456******3456", m2, fi1, fs2, sl2, ct2, mod2);
        st1 = _sourceTransactionService.save(st1);
        _installmentService.save(new InstallmentDTO(null, 1, 130.00, st1));

        SourceTransactionDTO st2 = new SourceTransactionDTO(null, Instant.now(), 789456L, "", "4687F3", TransactionStatus.APPROVED, 2, 150.00, "123456******3456", m1, fi2, fs1, sl1, ct1, mod3);
        st2 = _sourceTransactionService.save(st2);
        _installmentService.save(new InstallmentDTO(null, 1, 75.00, st2));
        _installmentService.save(new InstallmentDTO(null, 2, 75.00, st2));

        SourceTransactionDTO st3 = new SourceTransactionDTO(null, Instant.now(), 456132L, "", "852465", TransactionStatus.APPROVED, 3, 100.00, "123456******3456", m3, fi2, fs1, sl1, ct3, mod3);
        st3 = _sourceTransactionService.save(st3);
        _installmentService.save(new InstallmentDTO(null, 1, 33.34, st3));
        _installmentService.save(new InstallmentDTO(null, 2, 200.00, st3));
        _installmentService.save(new InstallmentDTO(null, 3, 33.33, st3));

        st3 = _sourceTransactionService.findById(st3.getId());
        for (InstallmentDTO o : st3.getInstallments()) {
            if (o.getQuota() == 2) {
                o.setGrossAmount(33.33);
                _installmentService.update(o);
            }
        }

    }

}
