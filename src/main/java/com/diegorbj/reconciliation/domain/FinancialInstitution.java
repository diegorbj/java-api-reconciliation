package com.diegorbj.reconciliation.domain;

import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tb_financialInstitution")
public class FinancialInstitution implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Exclude
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @EqualsAndHashCode.Exclude
    @Column(nullable = false, unique = true)
    private String code;

    public FinancialInstitution(Long id, String name, FinancialInstitutionCode code) {
        this.id = id;
        this.name = name;
        this.setCode( code);
    }

    public FinancialInstitutionCode getCode(){
        return FinancialInstitutionCode.value(this.code);
    }

    public void setCode(FinancialInstitutionCode code){
        this.code = code.getCode();
    }

}
