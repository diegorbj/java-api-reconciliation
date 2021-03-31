package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.services.CardTypeService;
import com.diegorbj.reconciliation.services.FinancialInstitutionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reconciliation/v1/financialinstitutions")
public class FinancialInstitutionResource {

    @Autowired
    private FinancialInstitutionService _service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FinancialInstitution>> findAll() {
        List<FinancialInstitution> list = _service.findAll();
        if (list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FinancialInstitution> findById(@PathVariable("id") Long id) {
        FinancialInstitution obj = _service.findById(id);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FinancialInstitution> insert(@RequestBody String data) {
        FinancialInstitution obj = _service.insert(FinancialInstitutionService.toFinancialInstitution(new JSONObject(data)));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FinancialInstitution> update(@PathVariable("id") Long id, @RequestBody String data) {
        _service.update(id, FinancialInstitutionService.toFinancialInstitution(new JSONObject(data)));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<FinancialInstitution> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
