package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.resources.utils.ResourceUtil;
import com.diegorbj.reconciliation.services.AuditInstallmentService;
import com.diegorbj.reconciliation.services.AuditOperationService;
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
@RequestMapping("/reconciliation/v1/operations/audits")
public class AuditOperationResource {

    @Autowired
    private AuditOperationService _service;

    @Autowired
    private AuditInstallmentService _childService;

    private static final Logger logger = Logger.getLogger(AuditOperationResource.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AuditOperationDTO>> findAll() {
        List<AuditOperationDTO> list = _service.findAll();
        logger.info(list);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AuditOperationDTO> findById(@PathVariable("id") Long id) {
        AuditOperationDTO obj = _service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<AuditOperationDTO> save(@RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                AuditOperationDTO obj = _service.save(AuditOperationDTO.fromJSON(data));
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
            throw new InvalidAttributeException("Not a valid auditing operation");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AuditOperationDTO> update(@PathVariable("id") Long id, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                AuditOperationDTO obj = _service.update(id, AuditOperationDTO.fromJSON(data));
                return ResponseEntity.ok().body(obj);
            } catch (Exception e) {
                logger.error("JSON fields are not parsable. " + e);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
        } else {
            throw new InvalidAttributeException("Not a valid auditing operation");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AuditOperationDTO> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/installments", method = RequestMethod.GET)
    public ResponseEntity<List<AuditInstallmentDTO>> findAllInstallments(@PathVariable("id") Long id) {
        List<AuditInstallmentDTO> list = _childService.findAllInstallments(id);
        logger.info(list);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}/installments/{quota}", method = RequestMethod.GET)
    public ResponseEntity<AuditInstallmentDTO> findInstallmentByQuota(@PathVariable("id") Long id, @PathVariable("quota") Integer quota) {
        AuditInstallmentDTO obj = _childService.getByAuditingOperationIdAndQuota(id, quota);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}/installments", method = RequestMethod.PUT)
    public ResponseEntity<AuditInstallmentDTO> saveInstallment(@PathVariable("id") Long id, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                AuditInstallmentDTO obj = AuditInstallmentDTO.fromJSON(data);
                obj.setOperation(_service.findById(id));
                obj = _childService.save(obj);
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
    public ResponseEntity<AuditInstallmentDTO> updateInstallment(@PathVariable("id") Long id, @PathVariable("quota") Integer quota, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                AuditInstallmentDTO obj = AuditInstallmentDTO.fromJSON(data);
                obj.setOperation(_service.findById(id));
                if (obj.getQuota().equals(quota)) {
                    obj = _childService.update(obj);
                    return ResponseEntity.ok().body(obj);
                } else {
                    throw new InvalidAttributeException("Inconsistent value for Quota");
                }
            } catch (Exception e) {
                logger.error("JSON fields are not parsable. " + e);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
        } else {
            throw new InvalidAttributeException("Not a valid source transaction");
        }
    }

    @RequestMapping(value = "/{id}/installments/{quota}", method = RequestMethod.DELETE)
    public ResponseEntity<AuditInstallmentDTO> deleteInstallment(@PathVariable("id") Long id, @PathVariable("quota") Integer quota) {
        _childService.delete(id, quota);
        return ResponseEntity.noContent().build();
    }

}
