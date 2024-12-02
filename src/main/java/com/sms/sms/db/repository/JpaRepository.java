package com.sms.sms.db.repository;

import java.util.List;

public interface JpaRepository<T,ID>{
    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    void delete(T entity);
    void deleteById(ID id);
    T update(ID id, T entity);
}
