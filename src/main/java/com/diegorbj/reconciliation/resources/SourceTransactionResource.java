package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.services.InstallmentService;
import com.diegorbj.reconciliation.services.SourceTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reconciliation/v1/sourcetransactions")
public class SourceTransactionResource {

    @Autowired
    private SourceTransactionService _service;

    @Autowired
    private InstallmentService _childService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SourceTransaction>> findAll() {
        List<SourceTransaction> list = _service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SourceTransaction> findById(@PathVariable("id") Long id) {
        SourceTransaction obj = _service.findById(id);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SourceTransaction> insert(@RequestBody SourceTransaction obj) {
        obj = _service.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SourceTransaction> update(@PathVariable("id") Long id, @RequestBody SourceTransaction obj) {
        _service.update(id, obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SourceTransaction> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/installments", method = RequestMethod.POST)
    public ResponseEntity<Installment> insert(@PathVariable("id") Long id, @RequestBody Installment obj) {
        if (obj.getQuota().getSourceTransaction().getId() != id){
            return ResponseEntity.badRequest().build();
        }
        obj = _childService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{number}")
                .buildAndExpand(obj.getQuota().getNumber())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }


}
