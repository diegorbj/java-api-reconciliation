package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.exceptions.DatabaseException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericService<T> {

    @Autowired
    protected JpaRepository<T, Long> _repository;

    public List<T> findAll() {
        return _repository.findAll();
    }

    public T findById(Long id) {
        Optional<T> obj = _repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFondException(id));
    }

    public T insert(T t) {
        return _repository.save(t);
    }

    public T update(Long id, T t) {
        T currentState = this.findById(id);
        updateData(t, currentState);
        return _repository.save(currentState);
    }

    public void delete(Long id) {
        Optional<T> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    abstract void updateData(T from, T to);

}
