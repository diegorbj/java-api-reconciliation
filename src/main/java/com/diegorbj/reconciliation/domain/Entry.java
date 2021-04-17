package com.diegorbj.reconciliation.domain;

import com.diegorbj.reconciliation.domain.enums.AmountType;
import com.diegorbj.reconciliation.domain.enums.EntryType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tb_entry")
public class Entry implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private EntryType entryType;
    @Column(nullable = false)
    private AmountType amountType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Entry(Long id, Double amount, EntryType entryType) {
        this.id = id;
        this.amount = amount;
        this.entryType = entryType;
    }

}
