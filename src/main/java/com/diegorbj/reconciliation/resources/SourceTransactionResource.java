package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.services.InstallmentService;
import com.diegorbj.reconciliation.services.SourceTransactionService;
import com.diegorbj.reconciliation.services.dto.*;
import org.json.JSONObject;
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
    public ResponseEntity<List<SourceTransactionDTO>> findAll() {
        List<SourceTransactionDTO> list = _service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SourceTransactionDTO> findById(@PathVariable("id") Long id) {
        SourceTransactionDTO obj = _service.findById(id);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SourceTransactionDTO> insert(@RequestBody String data) {
        SourceTransactionDTO obj = _service.insert(SourceTransactionDTO.fromJSON(data));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SourceTransactionDTO> update(@PathVariable("id") Long id, @RequestBody String data) {
        SourceTransactionDTO obj = _service.update(id, SourceTransactionDTO.fromJSON(data));
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SourceTransactionDTO> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
