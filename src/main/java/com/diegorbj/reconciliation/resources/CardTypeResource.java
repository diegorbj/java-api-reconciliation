package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.resources.utils.ResourceUtil;
import com.diegorbj.reconciliation.services.CardTypeService;
import com.diegorbj.reconciliation.services.dto.CardTypeDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
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
    public ResponseEntity<List<CardTypeDTO>> findAll() {
        List<CardTypeDTO> list = _service.findAll();
        logger.info(list);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CardTypeDTO> findById(@PathVariable("id") Long id) {
        CardTypeDTO obj = _service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CardTypeDTO> insert(@RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            CardTypeDTO obj = _service.insert(CardTypeDTO.fromJSON(data));
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(obj.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(obj);
        } else {
            throw new InvalidAttributeException("JSON is not valid");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CardTypeDTO> update(@PathVariable("id") Long id, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                CardTypeDTO obj = _service.update(id, CardTypeDTO.fromJSON(data));
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
    public ResponseEntity<CardTypeDTO> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
