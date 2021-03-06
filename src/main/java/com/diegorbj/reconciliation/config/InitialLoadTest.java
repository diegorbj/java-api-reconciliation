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
    private AuditOperationService _auditOperationService;

    @Autowired
    private AuditInstallmentService _auditInstallmentService;

    @Autowired
    private ConfirmOperationService _confirmOperationService;

    @Autowired
    private ConfirmInstallmentService _confirmInstallmentService;

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

        AuditOperationDTO ao1 = new AuditOperationDTO(null, Instant.now(), 123456L, null, "", "987D54", TransactionStatus.APPROVED, 1, 130.00, "123456******3456", "1234-ABC-56789", m2, fi1, fs2, sl2, ct2, mod2);
        ao1 = _auditOperationService.save(ao1);
        _auditInstallmentService.save(new AuditInstallmentDTO(null, 1, 130.00, ao1));

        AuditOperationDTO ao2 = new AuditOperationDTO(null, Instant.now(), null, 789456L, "", "4687F3", TransactionStatus.APPROVED, 2, 150.00, "123456******3456", "1234-ABC-56789", m1, fi2, fs1, sl1, ct1, mod3);
        ao2 = _auditOperationService.save(ao2);
        _auditInstallmentService.save(new AuditInstallmentDTO(null, 1, 75.00, ao2));
        _auditInstallmentService.save(new AuditInstallmentDTO(null, 2, 75.00, ao2));

        AuditOperationDTO ao3 = new AuditOperationDTO(null, Instant.now(), null, 456132L, "", "852465", TransactionStatus.APPROVED, 3, 100.00, "123456******3456", "1234-ABC-56789", m3, fi2, fs1, sl1, ct3, mod3);
        ao3 = _auditOperationService.save(ao3);
        _auditInstallmentService.save(new AuditInstallmentDTO(null, 1, 33.34, ao3));
        _auditInstallmentService.save(new AuditInstallmentDTO(null, 2, 200.00, ao3));
        _auditInstallmentService.save(new AuditInstallmentDTO(null, 3, 33.33, ao3));

        ao3 = _auditOperationService.findById(ao3.getId());
        for (AuditInstallmentDTO o : ao3.getInstallments()) {
            if (o.getQuota() == 2) {
                o.setGrossAmount(33.33);
                _auditInstallmentService.update(o);
            }
        }

        ConfirmOperationDTO co1 = new ConfirmOperationDTO(null, Instant.parse("2021-04-04T00:00:00.00Z"), 123456L,null, "", "987D54", TransactionStatus.APPROVED, 1, 130.00, "123456******3456", "1234-ABC-56789", m2, fi1, fs2, sl2, ct2, mod2);
        co1 = _confirmOperationService.save(co1);
        _confirmInstallmentService.save(new ConfirmInstallmentDTO(null, 1, 130.00, co1));

        ConfirmOperationDTO co2 = new ConfirmOperationDTO(null, Instant.parse("2021-04-05T00:00:00.00Z"),null, 789456L, "", "4687F3", TransactionStatus.CANCELED, 2, 150.00, "123456******3457", "1234-ABC-56789", m1, fi2, fs1, sl1, ct1, mod3);
        co2 = _confirmOperationService.save(co2);
        _confirmInstallmentService.save(new ConfirmInstallmentDTO(null, 1, 75.00, co2));
        _confirmInstallmentService.save(new ConfirmInstallmentDTO(null, 2, 75.00, co2));

        ConfirmOperationDTO co3 = new ConfirmOperationDTO(null, Instant.parse("2021-04-06T00:00:00.00Z"), null,456132L, "", "852465", TransactionStatus.APPROVED, 3, 100.00, "123456******3458", "1234-ABC-56789", m3, fi2, fs1, sl1, ct3, mod3);
        co3 = _confirmOperationService.save(co3);
        _confirmInstallmentService.save(new ConfirmInstallmentDTO(null, 1, 33.34, co3));
        _confirmInstallmentService.save(new ConfirmInstallmentDTO(null, 2, 200.00, co3));
        _confirmInstallmentService.save(new ConfirmInstallmentDTO(null, 3, 33.33, co3));

        co3 = _confirmOperationService.findById(co3.getId());
        for (ConfirmInstallmentDTO o : co3.getInstallments()) {
            if (o.getQuota() == 2) {
                o.setGrossAmount(33.33);
                _confirmInstallmentService.update(o);
            }
        }

    }

}
