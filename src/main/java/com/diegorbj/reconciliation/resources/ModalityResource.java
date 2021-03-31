package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.domain.Modality;
import com.diegorbj.reconciliation.services.ModalityService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reconciliation/v1/modalities")
public class ModalityResource {

    @Autowired
    private ModalityService _service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Modality>> findAll() {
        List<Modality> list = _service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Modality> findById(@PathVariable("id") Long id) {
        Modality obj = _service.findById(id);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Modality> insert(@RequestBody String data) {
        Modality obj = _service.insert(ModalityService.toModality(new JSONObject(data)));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Modality> update(@PathVariable("id") Long id, @RequestBody String data) {
        _service.update(id, ModalityService.toModality(new JSONObject(data)));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Modality> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
