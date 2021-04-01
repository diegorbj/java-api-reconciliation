package com.diegorbj.reconciliation.services.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FinancialServiceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public FinancialServiceDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}