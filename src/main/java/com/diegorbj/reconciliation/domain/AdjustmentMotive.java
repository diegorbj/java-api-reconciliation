package com.diegorbj.reconciliation.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tb_adjustment_motive")
public class AdjustmentMotive implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String identification;
    @Column(nullable = false, length = 100)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "financial_institution_id")
    private FinancialInstitution financialInstitution;

    @ManyToOne
    @JoinColumn(name = "adjustment_type_id")
    private AdjustmentType adjustmentType;

    public AdjustmentMotive(Long id, String identification, String description, AdjustmentType adjustmentType, FinancialInstitution financialInstitution) {
        this.id = id;
        this.identification = identification;
        this.description = description;
        this.adjustmentType = adjustmentType;
        this.financialInstitution = financialInstitution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdjustmentMotive that = (AdjustmentMotive) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
