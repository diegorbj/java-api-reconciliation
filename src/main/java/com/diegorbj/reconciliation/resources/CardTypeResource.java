package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.resources.util.Util;
import com.diegorbj.reconciliation.services.CardTypeService;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if (list.isEmpty()) {
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
        if (Util.isJSONValid(data)) {
            try {
                CardType obj = _service.insert(CardTypeResource.toCardType(new JSONObject(data)));
                URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(obj.getId())
                        .toUri();
                return ResponseEntity.created(uri).body(obj);
            } catch (Exception e) {
                logger.error("JSON fields are not parsable. " + e);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
        } else {
            throw new InvalidAttributeException("Not a valid card type");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CardType> update(@PathVariable("id") Long id, @RequestBody String data) {
        if (Util.isJSONValid(data)) {
            try {
                CardType obj = _service.update(id, CardTypeResource.toCardType(new JSONObject(data)));
                return ResponseEntity.ok().body(obj);
            } catch (Exception e) {
                logger.error("JSON fields are not parsable. " + e);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
        } else {
            throw new InvalidAttributeException("Not a valid card type");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CardType> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

    public static CardType toCardType(JSONObject jsonObject) {
        CardType obj = new CardType();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name") == JSONObject.NULL ? null : jsonObject.get("name").toString());
        return obj;
    }

}
