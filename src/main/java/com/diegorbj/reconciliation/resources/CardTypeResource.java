package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.services.CardTypeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.apache.log4j.Logger;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reconciliation/v1/cardtypes")
public class CardTypeResource {

    @Autowired
    private CardTypeService _service;

    private static final Logger logger = Logger.getLogger(CardTypeResource.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CardType>> findAll() {
        List<CardType> list = _service.findAll();
        if (list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        logger.info(list);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CardType> findById(@PathVariable("id") Long id) {
        CardType obj = _service.findById(id);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CardType> insert(@RequestBody String data) {
        CardType obj = _service.insert(CardTypeService.toCardType(new JSONObject(data)));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CardType> update(@PathVariable("id") Long id, @RequestBody String data) {
        CardType obj = _service.update(id, CardTypeService.toCardType(new JSONObject(data)));
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CardType> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
