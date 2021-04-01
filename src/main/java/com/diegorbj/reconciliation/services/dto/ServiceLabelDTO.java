package com.diegorbj.reconciliation.services.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ServiceLabelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public ServiceLabelDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
