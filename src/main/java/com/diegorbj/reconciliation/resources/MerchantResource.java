package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.domain.Merchant;
import com.diegorbj.reconciliation.services.MerchantService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reconciliation/v1/merchants")
public class MerchantResource {

    @Autowired
    private MerchantService _service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Merchant>> findAll() {
        List<Merchant> list = _service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Merchant> findById(@PathVariable("id") Long id) {
        Merchant obj = _service.findById(id);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Merchant> insert(@RequestBody String data) {
        Merchant obj = _service.insert(MerchantService.toMerchant(new JSONObject(data)));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Merchant> update(@PathVariable("id") Long id, @RequestBody String data) {
        Merchant obj = _service.update(id, MerchantService.toMerchant(new JSONObject(data)));
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Merchant> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
