package com.example.sandbox.service;

import java.util.List;
import java.util.Map;

public interface ModelService<M, I> {
    List<M> findAll();
    M findOneByID(I id);
    List<M> findAllByID(List<I> idList);
    boolean existsById(Long id);
    M saveOne(M model);
    List<M> saveAll(List<M> modelList);
    M updateOneByID(I id, M model);
    List<M> updateAll(Map<I,M> modelMap);
    void deleteOneByID(I id);
    void deleteAllByID(List<I> idList);
}
