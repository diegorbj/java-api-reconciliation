package com.diegorbj.reconciliation.resources;

import com.diegorbj.reconciliation.domain.FinancialService;
import com.diegorbj.reconciliation.domain.ServiceLabel;
import com.diegorbj.reconciliation.resources.util.Util;
import com.diegorbj.reconciliation.services.ServiceLabelService;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private static final Logger logger = Logger.getLogger(CardTypeResource.class);

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
        if (Util.isJSONValid(data)) {
            try {
                ServiceLabel obj = _service.insert(ServiceLabelResource.toServiceLabel(new JSONObject(data)));
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
            throw new InvalidAttributeException("Not a valid service label");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ServiceLabel> update(@PathVariable("id") Long id, @RequestBody String data) {
        ServiceLabel obj = _service.update(id, ServiceLabelResource.toServiceLabel(new JSONObject(data)));
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ServiceLabel> delete(@PathVariable("id") Long id) {
        _service.delete(id);
        return ResponseEntity.noContent().build();
    }

    public static ServiceLabel toServiceLabel(JSONObject jsonObject) {
        ServiceLabel obj = new ServiceLabel();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null: Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name") == JSONObject.NULL ? null: jsonObject.get("name").toString());
        return obj;
    }

}
