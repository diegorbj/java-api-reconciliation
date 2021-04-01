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
    private Integer number;
    private Double grossAmount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sourceTransaction_id")
    private SourceTransaction sourceTransaction;

    public Installment(Long id, Integer number, Double grossAmount, SourceTransaction sourceTransaction) {
        this.id = id;
        this.number = number;
        this.grossAmount = grossAmount;
        this.setSourceTransaction(sourceTransaction);
    }

}
