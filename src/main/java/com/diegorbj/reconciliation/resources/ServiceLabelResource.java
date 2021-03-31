package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.domain.ServiceLabel;
import com.diegorbj.reconciliation.services.ServiceLabelService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reconciliation/v1/servicelabels")
public class ServiceLabelResource {

    @Autowired
    private ServiceLabelService _service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ServiceLabel>> findAll() {
        List<ServiceLabel> list = _service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServiceLabel> findById(@PathVariable("id") Long id) {
        ServiceLabel obj = _service.findById(id);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ServiceLabel> insert(@RequestBody String data) {
        ServiceLabel obj = _service.insert(ServiceLabelService.toServiceLabel(new JSONObject(data)));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ServiceLabel> update(@PathVariable("id") Long id, @RequestBody String data) {
        ServiceLabel obj = _service.update(id, ServiceLabelService.toServiceLabel(new JSONObject(data)));
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ServiceLabel> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
