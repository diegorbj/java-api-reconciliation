package com.diegorbj.reconciliation.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tb_adjustment")
public class Adjustment implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String identification;
    @Column(nullable = false, length = 100)
    private String motive;

    @ManyToOne
    @JoinColumn(name = "financialInstitution_id")
    private FinancialInstitution financialInstitution;

    @ManyToOne
    @JoinColumn(name = "adjustmentType_id")
    private AdjustmentType adjustmentType;

    public Adjustment(Long id, String identification, String motive, AdjustmentType adjustmentType, FinancialInstitution financialInstitution) {
        this.id = id;
        this.identification = identification;
        this.motive = motive;
        this.adjustmentType = adjustmentType;
        this.financialInstitution = financialInstitution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adjustment that = (Adjustment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
