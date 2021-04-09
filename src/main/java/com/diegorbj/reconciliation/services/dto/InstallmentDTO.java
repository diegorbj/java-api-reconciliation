package com.diegorbj.reconciliation.services.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class InstallmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    protected Long id;
    @EqualsAndHashCode.Exclude
    protected Integer quota;
    @EqualsAndHashCode.Exclude
    protected Double grossAmount;

    public InstallmentDTO(Long id, Integer quota, Double grossAmount) {
        this.id = id;
        this.quota = quota;
        this.grossAmount = grossAmount;
    }

}
