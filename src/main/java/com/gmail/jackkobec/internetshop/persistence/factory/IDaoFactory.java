package com.gmail.jackkobec.internetshop.persistence.factory;

import com.gmail.jackkobec.internetshop.persistence.dao.CommonDao;

/**
 * Created by Jack on 07.01.2017.
 */
public interface IDaoFactory {

        CommonDao getDao(DaoType daoType);
}
