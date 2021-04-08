package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.resources.utils.ResourceUtil;
import com.diegorbj.reconciliation.services.AuditingInstallmentService;
import com.diegorbj.reconciliation.services.AuditingOperationService;
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
@RequestMapping("/reconciliation/v1/auditing/operations")
public class AuditingOperationResource {

    @Autowired
    private AuditingOperationService _service;

    @Autowired
    private AuditingInstallmentService _childService;

    private static final Logger logger = Logger.getLogger(AuditingOperationResource.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AuditingOperationDTO>> findAll() {
        List<AuditingOperationDTO> list = _service.findAll();
        logger.info(list);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AuditingOperationDTO> findById(@PathVariable("id") Long id) {
        AuditingOperationDTO obj = _service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<AuditingOperationDTO> save(@RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                AuditingOperationDTO obj = _service.save(AuditingOperationDTO.fromJSON(data));
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
    public ResponseEntity<AuditingOperationDTO> update(@PathVariable("id") Long id, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                AuditingOperationDTO obj = _service.update(id, AuditingOperationDTO.fromJSON(data));
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
    public ResponseEntity<AuditingOperationDTO> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/installments", method = RequestMethod.GET)
    public ResponseEntity<List<AuditingInstallmentDTO>> findAllInstallments(@PathVariable("id") Long id) {
        List<AuditingInstallmentDTO> list = _childService.findAllInstallments(id);
        logger.info(list);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}/installments/{quota}", method = RequestMethod.GET)
    public ResponseEntity<AuditingInstallmentDTO> findInstallmentByQuota(@PathVariable("id") Long id, @PathVariable("quota") Integer quota) {
        AuditingInstallmentDTO obj = _childService.getByAuditingOperationIdAndQuota(id, quota);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}/installments", method = RequestMethod.PUT)
    public ResponseEntity<AuditingInstallmentDTO> saveInstallment(@PathVariable("id") Long id, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                AuditingInstallmentDTO obj = AuditingInstallmentDTO.fromJSON(data);
                obj.setAuditingOperation(_service.findById(id));
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
    public ResponseEntity<AuditingInstallmentDTO> updateInstallment(@PathVariable("id") Long id, @PathVariable("quota") Integer quota, @RequestBody String data) {
        if (ResourceUtil.isJSONValid(data)) {
            try {
                AuditingInstallmentDTO obj = AuditingInstallmentDTO.fromJSON(data);
                obj.setAuditingOperation(_service.findById(id));
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
    public ResponseEntity<AuditingInstallmentDTO> deleteInstallment(@PathVariable("id") Long id, @PathVariable("quota") Integer quota) {
        _childService.delete(id, quota);
        return ResponseEntity.noContent().build();
    }

}
