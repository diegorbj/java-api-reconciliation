package com.diegorbj.reconciliation.domain.pk;

import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InstallmentId implements Serializable {

    private final static long serialVersionUID = 1L;

    @JsonIgnore
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "sourceTransaction_id")
    private SourceTransaction sourceTransaction;
    @EqualsAndHashCode.Include
    private Integer number;

}
