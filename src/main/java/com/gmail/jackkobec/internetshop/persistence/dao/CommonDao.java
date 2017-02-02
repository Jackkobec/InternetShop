package com.gmail.jackkobec.internetshop.persistence.dao;

import java.util.List;

/**
 * <p>CommonDao interface for declare common methods.
 * E - Entity class,
 * K - Primary kay class.
 */
public interface CommonDao<E, K> {

    Integer addNewEntity(E entity);

    Integer updateEntityInfo(E entity);

    boolean deleteEntityById(K id);

    List<E> getAll();

    E getOneByID(K id);
}
