package com.diegorbj.reconciliation.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_adjustment_type")
public class AdjustmentType implements Serializable {

    private final static long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Exclude
    @Column(nullable = false, length = 50)
    private String name;

    public AdjustmentType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
