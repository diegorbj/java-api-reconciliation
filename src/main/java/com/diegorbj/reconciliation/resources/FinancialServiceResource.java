package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.domain.FinancialService;
import com.diegorbj.reconciliation.services.FinancialServiceService;
import com.diegorbj.reconciliation.services.GenericService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reconciliation/v1/financialservices")
public class FinancialServiceResource {

    @Autowired
    private GenericService<FinancialService> _service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FinancialService>> findAll() {
        List<FinancialService> list = _service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FinancialService> findById(@PathVariable("id") Long id) {
        FinancialService obj = _service.findById(id);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FinancialService> insert(@RequestBody String data) {
        FinancialService obj = _service.insert(FinancialServiceService.toFinancialService(new JSONObject(data)));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FinancialService> update(@PathVariable("id") Long id, @RequestBody String data) {
        FinancialService obj = _service.update(id, FinancialServiceService.toFinancialService(new JSONObject(data)));
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<FinancialService> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
