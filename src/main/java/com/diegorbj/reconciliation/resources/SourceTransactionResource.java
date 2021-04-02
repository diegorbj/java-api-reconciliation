package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.resources.utils.ResourceUtil;
import com.diegorbj.reconciliation.services.InstallmentService;
import com.diegorbj.reconciliation.services.SourceTransactionService;
import com.diegorbj.reconciliation.services.dto.*;
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
@RequestMapping("/reconciliation/v1/sourcetransactions")
public class SourceTransactionResource {

    @Autowired
    private SourceTransactionService _service;

    @Autowired
    private InstallmentService _childService;

    private static final Logger logger = Logger.getLogger(SourceTransactionResource.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SourceTransactionDTO>> findAll() {
        List<SourceTransactionDTO> list = _service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(list);
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
        if (ResourceUtil.isJSONValid(data)) {
            try {
                SourceTransactionDTO obj = _service.insert(SourceTransactionDTO.fromJSON(data));
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
            throw new InvalidAttributeException("Not a valid source transaction");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SourceTransactionDTO> update(@PathVariable("id") Long id, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                SourceTransactionDTO obj = _service.update(id, SourceTransactionDTO.fromJSON(data));
                return ResponseEntity.ok().body(obj);
            } catch (Exception e) {
                logger.error("JSON fields are not parsable. " + e);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
        } else {
            throw new InvalidAttributeException("Not a valid source transaction");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SourceTransactionDTO> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/installments", method = RequestMethod.GET)
    public ResponseEntity<List<InstallmentDTO>> findAllInstallments(@PathVariable("id") Long id) {
        List<InstallmentDTO> list = _childService.findAllInstallments(id);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(list);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}/installments/{quota}", method = RequestMethod.GET)
    public ResponseEntity<InstallmentDTO> findInstallmentByQuota(@PathVariable("id") Long id, @PathVariable("quota") Integer quota) {
        InstallmentDTO obj = _childService.findByQuota(id, quota);
        if (obj == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}/installments", method = RequestMethod.POST)
    public ResponseEntity<InstallmentDTO> insertInstallment(@PathVariable("id") Long id, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                InstallmentDTO obj = InstallmentDTO.fromJSON(data);
                obj.setSourceTransaction(_service.findById(id));
                obj = _childService.insert(obj);
                URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{quota}")
                        .buildAndExpand(obj.getQuota())
                        .toUri();
                return ResponseEntity.created(uri).body(obj);
            } catch (Exception e) {
                logger.error("JSON fields are not parsable. " + e);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
        } else {
            throw new InvalidAttributeException("Not a valid source transaction");
        }
    }

    @RequestMapping(value = "/{id}/installments/{quota}", method = RequestMethod.PUT)
    public ResponseEntity<InstallmentDTO> updateInstallment(@PathVariable("id") Long id, @PathVariable("quota") Integer quota, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                InstallmentDTO obj = InstallmentDTO.fromJSON(data);
                SourceTransactionDTO parentObj = new SourceTransactionDTO();
                parentObj.setId(id);
                obj.setSourceTransaction(parentObj);
                obj = _childService.update(id, quota, obj);
                return ResponseEntity.ok().body(obj);
            } catch (Exception e) {
                logger.error("JSON fields are not parsable. " + e);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
        } else {
            throw new InvalidAttributeException("Not a valid source transaction");
        }
    }

    @RequestMapping(value = "/{id}/installments/{quota}", method = RequestMethod.DELETE)
    public ResponseEntity<InstallmentDTO> delete(@PathVariable("id") Long id, @PathVariable("quota") Long quota) {
        _childService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
