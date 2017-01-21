package com.gmail.jackkobec.internetshop.persistence.dao;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public interface CommonDao<E, K> {

    Integer addNewEntity(E entity);

    Integer updateEntityInfo(E entity);

    boolean deleteEntityById(K id);

    List<E> getAll();

    E getOneByID(K id);

}
