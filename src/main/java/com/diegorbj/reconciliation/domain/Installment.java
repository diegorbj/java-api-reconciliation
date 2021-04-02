package com.diegorbj.reconciliation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_installment")
public class Installment implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quota;
    private Double grossAmount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sourceTransactionId")
    private SourceTransaction sourceTransaction;

    public Installment(Long id, Integer quota, Double grossAmount, SourceTransaction sourceTransaction) {
        this.id = id;
        this.quota = quota;
        this.grossAmount = grossAmount;
        this.sourceTransaction = sourceTransaction;
    }

    public Installment(Long id, Integer quota, Double grossAmount) {
        this.id = id;
        this.quota = quota;
        this.grossAmount = grossAmount;
    }

}
