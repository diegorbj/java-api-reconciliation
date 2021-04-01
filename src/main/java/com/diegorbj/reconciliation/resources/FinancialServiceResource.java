package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.services.FinancialServiceService;
import com.diegorbj.reconciliation.services.dto.FinancialServiceDTO;
import com.diegorbj.reconciliation.resources.utils.ResourceUtil;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reconciliation/v1/financialservices")
public class FinancialServiceResource {

    @Autowired
    private FinancialServiceService _service;

    private static final Logger logger = Logger.getLogger(CardTypeResource.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FinancialServiceDTO>> findAll() {
        List<FinancialServiceDTO> list = _service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FinancialServiceDTO> findById(@PathVariable("id") Long id) {
        FinancialServiceDTO obj = _service.findById(id);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FinancialServiceDTO> insert(@RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                FinancialServiceDTO obj = _service.insert(FinancialServiceDTO.fromJSON(data));
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
            throw new InvalidAttributeException("Not a valid financial service");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FinancialServiceDTO> update(@PathVariable("id") Long id, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                FinancialServiceDTO obj = _service.update(id, FinancialServiceDTO.fromJSON(data));
                return ResponseEntity.ok().body(obj);
            } catch (Exception e) {
                logger.error("JSON fields are not parsable. " + e);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
        } else {
            throw new InvalidAttributeException("Not a valid financial service");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<FinancialServiceDTO> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
