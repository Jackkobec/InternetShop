package com.gmail.jackkobec.internetshop.dao;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public interface  CommonDao<E, K> {

    boolean addNewEntity(E entity);

    boolean updateEntityInfo(E entity);

    boolean deleteEntityById(K id);

    List<E> getAll();

    E getOneByID(K id);

}
