package com.example.sandbox.service;

import java.util.List;
import java.util.Map;

public interface ModelService<M, I> {
    public List<M> findAll();
    public M findOneByID(I id);
    public List<M> findAllByID(List<I> idList);
    public M saveOne(M model);
    public List<M> saveAll(List<M> modelList);
    public M updateOneByID(I id, M model);
    public List<M> updateAll(Map<I,M> modelMap);
    public void deleteOneByID(I id);
    public void deleteAllByID(List<I> idList);

}
